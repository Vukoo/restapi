package com.gadek.restapi.config;

import com.auth0.jwt.JWT;
import lombok.Getter;
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
        havingValue = "http")
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    protected final Long expirationTime;
    protected final String secret;

    public RestAuthenticationSuccessHandler(
            @Value("${spring.security.token.expirationTime}") long expirationTime,
            @Value("${spring.security.token.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        clearAuthenticationAttributes(request);
    }
}
