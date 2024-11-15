using LinqToDB.Data;
using LinqToDB;
using LinqToDB.Configuration;
using Microsoft.Extensions.Configuration;
using json2sql.Model;

namespace json2sql.Context
{
    public class CinebotDB : DataConnection
    {
        private static string connectionString;

        public CinebotDB(string server = "localhost", int port = 3306) : base("MySql", BuildConnectionString(server, port))
        {
            if (string.IsNullOrEmpty(connectionString))
            {
                connectionString = BuildConnectionString(server, port);
                DataConnection.AddConfiguration("MySql", connectionString);
            }
        }

        private static string BuildConnectionString(string server, int port)
        {
            return $"Server={server};Port={port};Database=cinebotdb;Uid=master;Pwd=master;AllowPublicKeyRetrieval=True;SslMode=None;";
        }

        public ITable<Pelicula> Peliculas => this.GetTable<Pelicula>();
        public ITable<Genero> Generos => this.GetTable<Genero>();
        public ITable<Cine> Cines => this.GetTable<Cine>();
        public ITable<Cartelera> Carteleras => this.GetTable<Cartelera>();
    }
}