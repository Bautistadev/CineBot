using LinqToDB.Mapping;

namespace json2sql.Model;
[Table(Name = "Genero", IsColumnAttributeRequired = false)]
public class Genero
{
    [PrimaryKey]
    public int id { get; set; }
    public string nombre { get; set; }
}

