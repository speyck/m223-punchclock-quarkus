using M223_PunchclockFrontend.Models;
using System.Threading.Tasks;

namespace M223_PunchclockFrontend.Authentication
{
    public interface IAuthenticationService
    {
        Task<AuthorizationUserModel> Login(AuthenticationUserModel userForAuthentication);
        void Logout();
    }
}