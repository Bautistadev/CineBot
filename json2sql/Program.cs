using json2sql.Context;
using json2sql.Model;
using LinqToDB;
using LinqToDB.Data;
using System;
using System.IO;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using CommandLine;

namespace json2sql;
internal class Program
{
    class Options
    {
        //Archivo JSON de entrada (obligatorio)
        [Option('j', "json", Required = true, HelpText = "Ubicación del archivo json con las peliculas")]
        public string? JsonDir { get; set; }


        //Exportar a archivo (opcional)
        [Option('e', "export", Required = false, HelpText = "Exportar como script SQL")]
        public bool ExportToFile { get; set; }

        [Option('n', "name", Required = false, HelpText = "Nombre del script SQL exportado")]
        public string? ExportName { get; set; }


        //Guardar en la base de datos (opcional)
        [Option('s', "save", Required = false, HelpText = "Guardar en la base de datos")]
        public bool SaveToDB { get; set; }

        [Option('h', "host", Required = false, HelpText = "Dirección del servidor")]
        public string? Host { get; set; }

        [Option('p', "port", Required = false, HelpText = "Puerto del servidor")]
        public int? Port { get; set; }
    }

    private static string filePath, host = "localhost", exportName = "script_cinebot.sql";
    private static int port = 3306;
    private static List<Genero> generosFromJson;
    private static List<Pelicula> peliculasFromJson;
    private static List<Cartelera> cartelerasFromJson;
    private static List<Cine> cines;

    static async Task Main(string[] args)
    {
        Console.WriteLine("---Iniciada ejecución del programa---");

        bool exportToFile = false, saveToDb = false;

        Parser.Default  .ParseArguments<Options>(args)
                        .WithParsed<Options>(opts =>
                        {
                            if(opts.JsonDir != null) 
                                filePath = opts.JsonDir;

                            exportToFile = opts.ExportToFile;
                            saveToDb = opts.SaveToDB;

                            if(!exportToFile && !saveToDb)
                            {
                                Console.WriteLine("No se selecciono ninguna de las dos opciones. Cerrando la aplicación.");
                                return;
                            }

                            if(opts.ExportName != null)
                                exportName = opts.ExportName;
                            if(opts.Host != null)
                                host = opts.Host;
                            if(opts.Port.HasValue)
                                port = opts.Port.Value;
                        })
                        .WithNotParsed(errors =>
                        {
                            Console.WriteLine("Error al procesar los argumentos.");
                        });

        await ReadJson();

        if (exportToFile)
        {
            string script = CreateSqlScript();
            await SaveScriptFile(script, exportName);
        }

        if (saveToDb)
            await PersistInDatabase();
    }

    public static async Task PersistInDatabase()
    {
        Console.WriteLine("---Guardando en la base de datos---");

        using (var db = new CinebotDB(host, port))
        {
            await db.BeginTransactionAsync();
            try
            { 
                //Chequeamos si la tabla esta vacia y persistimos los cines

                bool anyCines = await db.Cines.AnyAsync();
                if (!anyCines)
                {
                    await db.BulkCopyAsync(cines);
                }

                //Chequeamos si ya existen y persistimos los generos nuevos

                List<Genero> existingGeneros = await db.Generos.ToListAsync();
                int idLastGenero = -1;
                if(existingGeneros.Count() > 0)
                    idLastGenero = existingGeneros.Max(x => x.id); //Porque en la BD no es autoincremental
                foreach (var generoFromJson in generosFromJson)
                {
                    var genero = existingGeneros.FirstOrDefault(x => x.nombre == generoFromJson.nombre);
                    if(genero == null)
                    {
                        idLastGenero++;

                        generoFromJson.JsonId = generoFromJson.id;
                        generoFromJson.id = idLastGenero;
                        bool result = await db.InsertAsync(generoFromJson) >= 1;
                        if(result)
                            existingGeneros.Add(generoFromJson);
                        else
                            Console.WriteLine($"No se puede insertar genero: {generoFromJson.nombre}.");
                    }
                    else
                        genero.JsonId = generoFromJson.id;
                }

                //Chequeamos si ya existen y persistimos las peliculas nuevas

                foreach (var pelicula in peliculasFromJson)
                {
                    pelicula.Genero_id = existingGeneros.Where(x => x.JsonId == pelicula.Genero_id).Select(x => x.id).First();
                }
                var idPeliculasFromJson = peliculasFromJson.Select(x => x.id);
                var idExistingPeliculas = await db.Peliculas.Where(x => idPeliculasFromJson.Contains(x.id)).Select(x => x.id).ToListAsync();
                peliculasFromJson.RemoveAll(x => idExistingPeliculas.Contains(x.id));
        
                if(peliculasFromJson.Count() > 0)
                    await db.BulkCopyAsync(peliculasFromJson);

                //Chequeamos si ya existen y persistimos las carteleras nuevas

                var idCartelerasFromJson = cartelerasFromJson.Select(x => x.id);
                var idExistingCarteleras = await db.Carteleras.Where(x => idCartelerasFromJson.Contains(x.id)).Select(x => x.id).ToListAsync();
                cartelerasFromJson.RemoveAll(x => idExistingCarteleras.Contains(x.id));

                if (cartelerasFromJson.Count() > 0)
                    await db.BulkCopyAsync(cartelerasFromJson);

                await db.CommitTransactionAsync();
                Console.WriteLine($"La base de datos fue actualizada exitosamente con las nuevas peliculas.");
            }
            catch (Exception ex)
            {
                await db.RollbackTransactionAsync();
                Console.WriteLine("Error al conectarse a la base de datos. Razón: ");
                Console.WriteLine(ex.ToString());
                return;
            }
        }
    }

    public static async Task ReadJson()
    {
        if (!File.Exists(filePath))
        {
            Console.WriteLine($"El archivo {filePath} no existe.");
            return;
        }

        try
        {
            Console.WriteLine("---Leyendo archivo JSON---");
            // Lee y deserializa el archivo JSON
            string jsonContent = await File.ReadAllTextAsync(filePath);
            var options = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };
            List<PeliculaJson> peliculasJson = JsonSerializer.Deserialize<List<PeliculaJson>>(jsonContent, options);

            if (peliculasJson == null || peliculasJson.Count() == 0)
            {
                Console.WriteLine("El archivo JSON no contiene peliculas.");
                return;
            }

            cines = new List<Cine>
            {
                new Cine { id = 1, nombre = "CINEMA CITY", calle = "50", numero = "723" },
                new Cine { id = 2, nombre = "CINEMA OCHO", calle = "8", numero = "981" },
                new Cine { id = 3, nombre = "SAN MARTIN", calle = "7", numero = "923" },
                new Cine { id = 4, nombre = "PARADISO", calle = "46", numero = "780" },
                new Cine { id = 5, nombre = "CINEMA ROCHA", calle = "49", numero = "0" }
            };

            generosFromJson = new List<Genero>();
            peliculasFromJson = new List<Pelicula>();
            cartelerasFromJson = new List<Cartelera>();
            int generoId = 1;

            foreach (var peliculaJson in peliculasJson)
            {
                //Generos
                var genero = generosFromJson.Find(g => g.nombre == peliculaJson.Genre);
                if (genero == null)
                {
                    genero = new Genero { id = generoId++, nombre = TruncateString(peliculaJson.Genre, 45) };
                    generosFromJson.Add(genero);
                }

                //Peliculas
                int duracion = 0;
                if (!int.TryParse(peliculaJson.LengthMinutes.Replace(" min", ""), out duracion))
                {
                    Console.WriteLine($"Advertencia: duración no válida para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}, se guardo 0 de duración.");
                    duracion = 0;
                }
                bool idPeliculaValido = int.TryParse(peliculaJson.MovieId, out int idPelicula);
                if (!idPeliculaValido)
                {
                    Console.WriteLine($"Advertencia: id de pelicula no valido para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}.");
                    idPelicula = 0;
                }
                var pelicula = new Pelicula
                {
                    id = idPelicula,
                    nombre = TruncateString(peliculaJson.Title, 45),
                    Genero_id = genero.id,
                    director = TruncateString(peliculaJson.Director, 45),
                    duracion = duracion
                };
                if (!peliculasFromJson.Any(x => x.id == pelicula.id))
                    peliculasFromJson.Add(pelicula);

                //Cartelera
                var cine = cines.Find(c => c.nombre == peliculaJson.Cinema);
                if (cine == null)
                {
                    Console.WriteLine($"Advertencia: cine '{peliculaJson.Cinema}' no encontrado. bookingId: {peliculaJson.BookingId}.");
                    continue;
                }

                DateOnly fecha = new DateOnly(2024, 1, 1);
                TimeOnly hora = new TimeOnly(0, 0);

                var fechaSplit = peliculaJson.Date.Split('/');
                bool fechaValida = fechaSplit.Length == 2 &&
                                   int.TryParse(fechaSplit[0], out int dia) &&
                                   int.TryParse(fechaSplit[1], out int mes) &&
                                   DateOnly.TryParse($"{2024}-{mes}-{dia}", out fecha);

                if (!fechaValida)
                    Console.WriteLine($"Advertencia: fecha no válida para '{peliculaJson.Title}' con bookingId:  {peliculaJson.BookingId}.");

                bool horaValida = TimeOnly.TryParse(peliculaJson.Time, out hora);
                if (!horaValida)
                    Console.WriteLine($"Advertencia: hora no válida para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}.");

                bool idCarteleraValido = int.TryParse(peliculaJson.BookingId, out int idCartelera);
                if (!idCarteleraValido)
                    Console.WriteLine($"Advertencia: id de pelicula no valido para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}.");
                else
                {
                    var carteleraItem = new Cartelera
                    {
                        id = idCartelera,
                        Fecha = fecha,
                        Hora = hora,
                        Pelicula_Id = pelicula.id,
                        Cine_Id = cine.id
                    };
                    cartelerasFromJson.Add(carteleraItem);
                }
            }

        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al procesar el archivo JSON: {ex.Message}");
        }
    }

    public static string CreateSqlScript()
    {
        Console.WriteLine("---Creando script SQL---");
        StringBuilder script = new StringBuilder();

        script.AppendLine("USE cinebotdb;");
        script.AppendLine();

        script.AppendLine("-- Tabla Genero");
        foreach (var genero in generosFromJson)
        {
            script.AppendLine($"INSERT INTO Genero (id, nombre) VALUES ({genero.id}, '{genero.nombre.Replace("'", "''")}');");
        }
        script.AppendLine();

        script.AppendLine("-- Tabla Pelicula");
        foreach (var pelicula in peliculasFromJson)
        {
            script.AppendLine($"INSERT INTO Pelicula (id, nombre, Genero_id, director, duracion) VALUES ({pelicula.id}, '{pelicula.nombre.Replace("'", "''")}', {pelicula.Genero_id}, '{pelicula.director.Replace("'", "''")}', {pelicula.duracion});");
        }
        script.AppendLine();

        script.AppendLine("-- Tabla Cine");
        foreach (var cine in cines)
        {
            script.AppendLine($"INSERT INTO Cine (id, nombre, calle, numero) VALUES ({cine.id}, '{cine.nombre.Replace("'", "''")}', '{cine.calle}', '{cine.numero}');");
        }
        script.AppendLine();

        script.AppendLine("-- Tabla Cartelera");
        foreach (var carteleraItem in cartelerasFromJson)
        {
            script.AppendLine($"INSERT INTO Cartelera (id, fecha, hora, Pelicula_Id, Cine_Id) VALUES ({carteleraItem.id}, '{carteleraItem.Fecha:yyyy-MM-dd}', '{carteleraItem.Hora:HH\\:mm\\:ss}', {carteleraItem.Pelicula_Id}, {carteleraItem.Cine_Id});");
        }

        return script.ToString();
    }

    public static async Task SaveScriptFile(string script, string filePath)
    {
        try
        {
            await File.WriteAllTextAsync(filePath, script);
            Console.WriteLine($"Script SQL guardado exitosamente en {filePath}");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al guardar el archivo SQL: {ex.Message}");
        }
    }

    public static string TruncateString(string input, int length)
    {
        if (input == null)
            return string.Empty; // Retorna vacío si el string es null

        if (length < 0)
            throw new ArgumentOutOfRangeException(nameof(length), "La longitud no puede ser negativa.");

        return input.Length > length ? input.Substring(0, length) : input;
    }
}
