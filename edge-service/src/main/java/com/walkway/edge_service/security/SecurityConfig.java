package com.walkway.edge_service.security;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;


@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${application.service.frontend.url}")
    private String frontendUrl;

    @Order(0)
    @Bean
    public SecurityWebFilterChain actuatorFilterChain(ServerHttpSecurity http){
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator/**").permitAll());
        return http.build();
    }

    @Order(1)
    @Bean
    public SecurityWebFilterChain accountUserFilterChain(ServerHttpSecurity http){
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/account/user/**"))
                .cors(cors-> cors.configurationSource(new FrontendCORSConfig(frontendUrl)))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/account/user/**").authenticated()
                )
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

    @Order(2)
    @Bean
    public SecurityWebFilterChain accountRegisterFilterChain(ServerHttpSecurity http){
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/account/register"))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/account/register").permitAll()
                );
        return http.build();
    }

    @Order(3)
    @Bean
    public SecurityWebFilterChain swaggerUiFilterChain(ServerHttpSecurity http){
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/swagger/**"))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/swagger/**").authenticated())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

    @Order(4)
    @Bean
    public SecurityWebFilterChain authenticationFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/oauth2/**").permitAll() // Endpoint to hit to initiate the PKCE login flow
                        .pathMatchers("/login/**").permitAll() // Endpoint to accept the redirect form the browser to accept the Token for the exchange of accessToken
                        .anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

}
