using Newtonsoft.Json;
using System.Collections.Generic;

namespace M223_PunchclockFrontend.Models
{
    public class LocationModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("address")]
        public string Address { get; set; }

        [JsonProperty("zip")]
        public int Zip { get; set; }

        [JsonProperty("city")]
        public string City { get; set; }

        [JsonProperty("entries")]
        public List<EntryModel> Entries { get; set; }
    }
}
