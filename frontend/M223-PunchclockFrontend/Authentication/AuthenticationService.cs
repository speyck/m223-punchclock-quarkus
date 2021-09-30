using M223_PunchclockFrontend.Models;
using Microsoft.AspNetCore.Components.Authorization;
using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Http.Json;
using System.Threading.Tasks;

namespace M223_PunchclockFrontend.Authentication
{
    public class AuthenticationService : IAuthenticationService
    {
        private readonly HttpClient httpClient;
        private readonly AuthenticationStateProvider authenticationState;
        private readonly AuthenticationResolveService resolver;
        private const string API_PATH = "/api/auth/login";

        public AuthenticationService(HttpClient client, AuthenticationStateProvider authenticationState, AuthenticationResolveService resolver)
        {
            (httpClient = client).BaseAddress = new Uri("http://localhost:8080/");
            this.authenticationState = authenticationState;
            this.resolver = resolver;
        }

        public async Task<AuthorizationUserModel> Login(AuthenticationUserModel userForAuthentication)
        {
            HttpResponseMessage authResult = await httpClient.PostAsJsonAsync(API_PATH, userForAuthentication);

            if (authResult.IsSuccessStatusCode == false)
            {
                return null;
            }

            string jwt = await authResult.Content.ReadAsStringAsync();

            AuthorizationUserModel authContent = new()
            {
                Token = jwt,
                Username = userForAuthentication.Username
            };

            resolver.AuthorizationUserModel = authContent;

            ((AuthStateProvider)authenticationState).NotifyUserAuthentication(authContent.Token);
            httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("bearer", authContent.Token);

            return authContent;
        }

        public void Logout()
        {
            resolver.AuthorizationUserModel = null;
            ((AuthStateProvider)authenticationState).NotifyUserLogout();
            httpClient.DefaultRequestHeaders.Authorization = null;
        }
    }
}
