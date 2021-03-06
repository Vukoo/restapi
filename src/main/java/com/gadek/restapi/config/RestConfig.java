package com.gadek.restapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import static java.util.Collections.singletonList;


@Configuration
@EnableSwagger2
@EnableCaching
public class RestConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType. SWAGGER_2 )
                .ignoredParameterTypes(UsernamePasswordAuthenticationToken.class)
                .select()
//                .apis(RequestHandlerSelectors
//                        .basePackage("com.gadek.restapi.controller"))
                .paths(PathSelectors. regex ( "^(?!/(error).*$).*$" ))
                .build()
                .securitySchemes(singletonList(createSchema()))
                .securityContexts(singletonList(createContext()));
    }

    private SecurityContext createContext() {
        return SecurityContext.builder().securityReferences(createRef()).build();
    }

    private List<SecurityReference> createRef() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}