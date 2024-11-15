using LinqToDB.Mapping;

namespace json2sql.Model;
[Table(Name = "Cine", IsColumnAttributeRequired = false)]
public class Cine
{
    [PrimaryKey]
    public int id { get; set; }
    public string nombre { get; set; }
    public string calle { get; set; }
    public string numero { get; set; }
}

