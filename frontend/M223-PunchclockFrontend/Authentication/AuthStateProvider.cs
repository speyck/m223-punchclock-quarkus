using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Http;
using System.Net.Http;
using System.Security.Claims;
using System.Threading.Tasks;

namespace M223_PunchclockFrontend.Authentication
{
    public class AuthStateProvider : AuthenticationStateProvider
    {
        private AuthenticationState _authenticationState;
        private readonly AuthenticationResolveService _resolver;

        public AuthStateProvider(AuthenticationResolveService resolver)
        {
            _authenticationState = new AuthenticationState(new ClaimsPrincipal(new ClaimsIdentity()));
            _resolver = resolver;
        }

        public void NotifyUserAuthentication(string token)
        {
            ClaimsPrincipal authenticatedUser = new(new ClaimsIdentity(JwtParser.ParseClaimsFromJwt(token), "jwtAuthType"));
            Task<AuthenticationState> authState = Task.FromResult(new AuthenticationState(authenticatedUser));

            NotifyAuthenticationStateChanged(authState);
        }

        public void NotifyUserLogout()
        {
            Task<AuthenticationState> authState = Task.FromResult(_authenticationState);
            NotifyAuthenticationStateChanged(authState);
        }

        public async override Task<AuthenticationState> GetAuthenticationStateAsync()
        {
            string token = _resolver.AuthorizationUserModel?.Token;

            await Task.CompletedTask;

            if (string.IsNullOrWhiteSpace(token))
            {
                _authenticationState = new AuthenticationState(new ClaimsPrincipal(new ClaimsIdentity()));
            }
            else
            {
                _authenticationState = new AuthenticationState(
                                            new ClaimsPrincipal(
                                                new ClaimsIdentity(JwtParser.ParseClaimsFromJwt(token), "jwtAuthType")));
            }

            return _authenticationState;
        }
    }
}
