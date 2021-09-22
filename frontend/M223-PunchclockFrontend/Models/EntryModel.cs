using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
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
    }
}
