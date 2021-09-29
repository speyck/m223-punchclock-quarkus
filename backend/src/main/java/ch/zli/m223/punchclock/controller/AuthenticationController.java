package ch.zli.m223.punchclock.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;

@Path("/api/auth")
public class AuthenticationController {

    @Inject
    private AuthenticationService authService;

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user) {
        if (!authService.checkIfUserExists(user)) {
            throw new NotAuthorizedException("User \'" + user.getUsername() + "\' with password \'" + user.getPassword() + "\' could not be authenticated" , Response.Status.UNAUTHORIZED);
        }

        return authService.GenerateValidJwtToken(user.getUsername());
    }
}
