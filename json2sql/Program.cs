using json2sql.Model;
using System;
using System.IO;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace json2sql;
internal class Program
{
    static async Task Main(string[] args)
    {
        Console.WriteLine("Iniciada ejecución del programa");
        if (args.Length == 0)
        {
            Console.WriteLine("Por favor, proporciona la ruta del archivo JSON como argumento.");
            return;
        }
        string filePath = args[0];

        if (!File.Exists(filePath))
        {
            Console.WriteLine($"El archivo {filePath} no existe.");
            return;
        }

        try
        {
            // Lee y deserializa el archivo JSON
            string jsonContent = await File.ReadAllTextAsync(filePath);
            var options = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };
            List<PeliculaJson> peliculasJson = JsonSerializer.Deserialize<List<PeliculaJson>>(jsonContent, options);
            
            if(peliculasJson == null || peliculasJson.Count() == 0)
            {
                Console.WriteLine("El archivo JSON no contiene peliculas");
                return;
            }

            List<Cine> cines = new List<Cine>
            {
                new Cine { id = 1, nombre = "CINEMA CITY", calle = "50", numero = "723" },
                new Cine { id = 2, nombre = "CINEMA OCHO", calle = "8", numero = "981" },
                new Cine { id = 3, nombre = "SAN MARTIN", calle = "7", numero = "923" },
                new Cine { id = 4, nombre = "PARADISO", calle = "46", numero = "780" },
                new Cine { id = 5, nombre = "CINEMA ROCHA", calle = "49", numero = "0" }
            };
            
            List<Genero> generos = new List<Genero>();
            List<Pelicula> peliculas = new List<Pelicula>();
            List<Cartelera> cartelera = new List<Cartelera>();
            int generoId = 1;

            foreach (var peliculaJson in peliculasJson)
            {
                //Generos
                var genero = generos.Find(g => g.nombre == peliculaJson.Genre);
                if (genero == null)
                {
                    genero = new Genero { id = generoId++, nombre = peliculaJson.Genre };
                    generos.Add(genero);
                }

                //Peliculas
                int duracion = 0;
                if (!int.TryParse(peliculaJson.LengthMinutes.Replace(" min", ""), out duracion))
                {
                    Console.WriteLine($"Advertencia: duración no válida para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}");
                    duracion = 0; 
                }
                bool idPeliculaValido = int.TryParse(peliculaJson.MovieId, out int idPelicula);
                if (!idPeliculaValido)
                {
                    Console.WriteLine($"Advertencia: id de pelicula no valido para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}");
                    idPelicula = 0;
                }            
                var pelicula = new Pelicula
                {
                    id = idPelicula,
                    nombre = peliculaJson.Title,
                    Genero_id = genero.id,
                    director = peliculaJson.Director,
                    duracion = duracion
                };
                if (!peliculas.Any(x => x.id == pelicula.id))
                    peliculas.Add(pelicula);

                //Cartelera
                var cine = cines.Find(c => c.nombre == peliculaJson.Cinema);
                if (cine == null)
                {
                    Console.WriteLine($"Advertencia: cine '{peliculaJson.Cinema}' no encontrado. bookingId: {peliculaJson.BookingId}");
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
                    Console.WriteLine($"Advertencia: fecha no válida para '{peliculaJson.Title}' con bookingId:  {peliculaJson.BookingId}");

                bool horaValida = TimeOnly.TryParse(peliculaJson.Time, out hora);
                if (!horaValida)
                    Console.WriteLine($"Advertencia: hora no válida para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}");

                bool idCarteleraValido = int.TryParse(peliculaJson.BookingId, out int idCartelera);
                if (!idCarteleraValido)
                    Console.WriteLine($"Advertencia: id de pelicula no valido para '{peliculaJson.Title}' con bookingId: {peliculaJson.BookingId}");
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
                    cartelera.Add(carteleraItem);
                }
            }

            string script = CreateSqlScript(cines, generos, peliculas, cartelera);
            await SaveScriptFile(script, "script_insercion.sql");
            
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al procesar el archivo JSON: {ex.Message}");
        }
    }

    public static string CreateSqlScript(List<Cine> cines, List<Genero> generos, List<Pelicula> peliculas, List<Cartelera> cartelera)
    {
        StringBuilder script = new StringBuilder();

        script.AppendLine("USE cinebotdb;");
        script.AppendLine();

        script.AppendLine("-- Tabla Genero");
        foreach (var genero in generos)
        {
            script.AppendLine($"INSERT INTO Genero (id, nombre) VALUES ({genero.id}, '{genero.nombre.Replace("'", "''")}');");
        }
        script.AppendLine();

        script.AppendLine("-- Tabla Pelicula");
        foreach (var pelicula in peliculas)
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
        foreach (var carteleraItem in cartelera)
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
}
