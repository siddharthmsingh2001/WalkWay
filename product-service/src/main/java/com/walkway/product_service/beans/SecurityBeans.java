package com.walkway.product_service.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkway.product_service.security.AuthorizationDeniedHandler;
import com.walkway.product_service.security.BearerAuthenticationEntryPoint;
import com.walkway.product_service.security.KeycloakRoleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityBeans {

    @Value("${spring.application.name}")
    private String resourceName;

    @Value("${application.iam.keycloak.resourceserver.jwt.iss}")
    private String jwtIssuer;

    @Value("${application.iam.keycloak.resourceserver.jwt.jwk-set-uri}")
    private String jwtJwkSetUri;

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter(resourceName));
        return converter;
    }

    @Bean
    public AuthenticationEntryPoint bearerAuthenticationEntryPoint(ObjectMapper objectMapper) {
        return new BearerAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public AccessDeniedHandler authorizationDeniedHandler(ObjectMapper objectMapper){
        return new AuthorizationDeniedHandler(objectMapper);
    }

    // Added because "iss" claim in the token is localhost:8081 while we are making a request to keycloak:8080 which leds to mismatch.
    @Bean
    public JwtDecoder jwtDecoder(){
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwtJwkSetUri).build();
        OAuth2TokenValidator<Jwt> issuerValidator = new JwtIssuerValidator(jwtIssuer);
        OAuth2TokenValidator<Jwt> timestampValidator = new JwtTimestampValidator();
        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(issuerValidator, timestampValidator));
        return jwtDecoder;
    }
}
