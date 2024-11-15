using System;
using System.Text.Json.Serialization;

namespace json2sql.Model;

public class PeliculaJson
{
    [JsonPropertyName("movieId")] public string MovieId { get; set; }
    [JsonPropertyName("title")] public string Title { get; set; }
    [JsonPropertyName("imageLink")] public string ImageLink { get; set; }
    [JsonPropertyName("lengthMinutes")] public string LengthMinutes { get; set; }
    [JsonPropertyName("genre")] public string Genre { get; set; }
    [JsonPropertyName("director")] public string Director { get; set; }
    [JsonPropertyName("cast")] public string Cast { get; set; }
    [JsonPropertyName("bookingId")] public string BookingId { get; set; }
    [JsonPropertyName("date")] public string Date { get; set; }
    [JsonPropertyName("time")] public string Time { get; set; }
    [JsonPropertyName("cinema")] public string Cinema { get; set; }
    [JsonPropertyName("typeAndLanguage")] public string TypeAndLanguage { get; set; }
    [JsonPropertyName("bookLink")] public string BookLink { get; set; }
}

