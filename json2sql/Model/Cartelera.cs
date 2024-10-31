namespace json2sql.Model;
public class Cartelera
{
    public int id { get; set; }
    public DateOnly Fecha { get; set; }
    public TimeOnly Hora { get; set; }
    public int Pelicula_Id { get; set; }
    public int Cine_Id { get; set; }
}

