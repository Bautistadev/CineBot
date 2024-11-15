using LinqToDB.Data;
using LinqToDB.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using LinqToDB;
using json2sql.Model;

namespace json2sql.Context
{
    public class CinebotDB : DataConnection
    {
        public CinebotDB() : base("MySql", GetConnectionString())
        {
        }

        static CinebotDB()
        {
            // Configuración inicial de LinqToDB si es necesario
            DataConnection.AddConfiguration("MySql", GetConnectionString());
        }

        private static string GetConnectionString()
        {
            var configurationBuilder = new ConfigurationBuilder()
                .SetBasePath(AppContext.BaseDirectory)
                .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true);
                

            var config = configurationBuilder.Build();
            var result = config.GetConnectionString("CinebotdbConnection");
            result = "Server=localhost;Port=3306;Database=cinebotdb;Uid=master;Pwd=master;AllowPublicKeyRetrieval=True;SslMode=None;";
            return result;
        }

        public ITable<Pelicula> Peliculas => this.GetTable<Pelicula>();
        public ITable<Genero> Generos => this.GetTable<Genero>();
        public ITable<Cine> Cines => this.GetTable<Cine>();
        public ITable<Cartelera> Carteleras => this.GetTable<Cartelera>();
    }
}
