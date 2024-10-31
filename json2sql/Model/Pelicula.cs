namespace json2sql.Model;
public class Pelicula
{
    public int id { get; set; }
    public string nombre { get; set; }
    public int Genero_id { get; set; }
    public string director { get; set; }
    public int duracion { get; set; } //En minutos
}

