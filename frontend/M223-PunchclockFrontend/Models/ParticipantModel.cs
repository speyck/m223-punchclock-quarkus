using Newtonsoft.Json;

namespace M223_PunchclockFrontend.Models
{
    public class ParticipantModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("firstname")]
        public string Firstname { get; set; }

        [JsonProperty("lastname")]
        public string Lastname { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("pone")]
        public string Phone { get; set; }

        [JsonProperty("entry")]
        public EntryModel Entry { get; set; }
    }
}
