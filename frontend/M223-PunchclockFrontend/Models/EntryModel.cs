using System;
using Newtonsoft.Json;

namespace M223_PunchclockFrontend.Models
{
    public class EntryModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("checkIn")]
        public DateTime CheckIn { get; set; }

        [JsonProperty("checkOut")]
        public DateTime CheckOut { get; set; }

        [JsonProperty("category")]
        public CategoryModel Category { get; set; }
    }
}
