using System.ComponentModel.DataAnnotations;

namespace M223_PunchclockFrontend.Models
{
    public class AuthenticationUserModel
    {
        [Required]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }


    }
}
