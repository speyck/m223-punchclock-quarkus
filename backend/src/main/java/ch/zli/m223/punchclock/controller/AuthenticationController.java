package ch.zli.m223.punchclock.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;

@Path("/atuh")
public class AuthenticationController {

    @Inject
    private AuthenticationService authService;

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user) {
        // todo heck if user exists, if not then throw NotAutherizedException

        return authService.GenerateValidJwtToken(user.getUsername());
    }
}
