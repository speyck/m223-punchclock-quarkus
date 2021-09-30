using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Text.Json;

namespace M223_PunchclockFrontend.Authentication
{
    public class JwtParser
    {
        public static IEnumerable<Claim> ParseClaimsFromJwt(string jwt)
        {
            List<Claim> claims = new();
            string payload = jwt.Split('.')[1];

            byte[] jsonBytes = ParseBase64WithoutPadding(payload);

            Dictionary<string, object> keyValuePairs = JsonSerializer.Deserialize<Dictionary<string, object>>(jsonBytes);

            ExtractRolesFromJWT(claims, keyValuePairs);

            claims.AddRange(keyValuePairs.Select(pair => new Claim(pair.Key, pair.Value.ToString())));

            return claims;
        }

        private static void ExtractRolesFromJWT(List<Claim> claims, Dictionary<string, object> keyValuePairs)
        {
            keyValuePairs.TryGetValue(ClaimTypes.Role, out object roles);

            if (roles != null)
            {
                string[] parsedRoles = roles.ToString().Trim().TrimStart('[').TrimEnd(']').Split(',');

                if (parsedRoles.Length > 1)
                {
                    foreach (string parsedRole in parsedRoles)
                    {
                        claims.Add(new Claim(ClaimTypes.Role, parsedRole.Trim('"')));
                    }
                }
                else
                {
                    claims.Add(new Claim(ClaimTypes.Role, parsedRoles[0]));
                }

                keyValuePairs.Remove(ClaimTypes.Role);
            }
        }

        private static byte[] ParseBase64WithoutPadding(string base64)
        {
            switch (base64.Length % 4)
            {
                case 2:
                    base64 += "==";
                    break;
                case 3:
                    base64 += "=";
                    break;
            }
            return Convert.FromBase64String(base64);
        }
    }
}
