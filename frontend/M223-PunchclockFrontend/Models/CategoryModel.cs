using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace M223_PunchclockFrontend.Models
{
    public class CategoryModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("entries")]
        public List<EntryModel> Entries { get; set; }
    }
}
