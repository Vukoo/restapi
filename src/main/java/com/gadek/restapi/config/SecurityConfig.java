package com.gadek.restapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value="spring.security.type",
        havingValue = "http")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected final RestAuthenticationFailureHandler authenticationFailureHandler;
    protected final RestAuthenticationSuccessHandler authenticationSuccessHandler;
    protected final ObjectMapper objectMapper;
    protected final DataSource dataSource;

    public SecurityConfig(RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          RestAuthenticationFailureHandler authenticationFailureHandler, ObjectMapper objectMapper, DataSource dataSource) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.objectMapper = objectMapper;
        this.dataSource = dataSource;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .withDefaultSchema()
                .dataSource(dataSource)
                .withUser("user")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("user"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 1
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui/").permitAll()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(objectMapper);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler); // 1
        filter.setAuthenticationFailureHandler(authenticationFailureHandler); // 2
        filter.setAuthenticationManager(super.authenticationManager()); // 3
        return filter;
    }
}