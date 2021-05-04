package com.gadek.restapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@ConditionalOnProperty(value="spring.security.type",
        havingValue = "jwt")
public class RestAuthenticationSuccessHandlerJWT extends RestAuthenticationSuccessHandler {


    public RestAuthenticationSuccessHandlerJWT(
            @Value("${spring.security.token.expirationTime}") long expirationTime,
            @Value("${spring.security.token.secret}") String secret) {
        super(expirationTime,secret);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
    }
}