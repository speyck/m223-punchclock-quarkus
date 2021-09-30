using System;
using System.Collections.Generic;
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

        [JsonProperty("location")]
        public LocationModel Location { get; set; }

        [JsonProperty("participants")]
        public List<ParticipantModel> Participants { get; set; }
    }
}
