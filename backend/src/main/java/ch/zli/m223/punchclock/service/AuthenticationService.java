package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.punchclock.domain.User;

@RequestScoped
public class AuthenticationService {

    @Inject
    private EntityManager entityManager;
    
    public String GenerateValidJwtToken(String username){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(username) 
            .groups(new HashSet<>(Arrays.asList("User"))) 
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

    public boolean checkIfUserExists(User user) {
        Query stmt = entityManager.createQuery("SELECT COUNT(*) FROM User WHERE username = :name AND password = :password");
        stmt.setParameter("name", user.getUsername());
        stmt.setParameter("password", user.getPassword());

        return (long)stmt.getSingleResult() == 1;
    }

}
