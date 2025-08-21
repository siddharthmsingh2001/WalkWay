package com.walkway.account_service.security;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
        "/v2/api-docs",
        "/v3/api-docs",
        "/v3/api-docs/swagger-config",
        "/v2/api-docs/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui/**",
        "/webjars/**",
        "/swagger-ui.html"
 */


// NOTE THAT ALL THE CORS CONFIG ARE JUST BECAUSE OF THE SWAGGER UI. THE CORS IS ONLY NEEDED IN THE edge-service. BUT SINCE THE localhost:8082 is making request to localhost:8083 which CROSS ORIGIN WE NEED TO CORS ALTHOUGH THIS CREATES PROBLEM IF WE HAVE A FRONTEND TRYING TO CONTACT THE DOWNSTREAM SERVICE BECAUSE THEN WE HAVE 2 CORS (one attached by the edge-service and one attached by the account-service) WHICH IS BLOCKED BY THE BROWSER.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    @Value("${application.service.edge-service.external-url}")
    private String edgeServiceUrl;

    @Bean
    @Order(0)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/actuator/**")
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/actuator/**").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain registrationFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/account/register")
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/account/register").anonymous())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain swaggerAuthorizeFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher(new AntPathRequestMatcher("/api/account/**", HttpMethod.OPTIONS.name()))
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/account/**").permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/account/user/**")
                .addFilterBefore(new RequestResponseLogFilter(), CorsFilter.class)
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/account/user/account").hasAuthority("READ_ACCOUNT")
                        .requestMatchers("/api/account/user/profile").hasAuthority("READ_PROFILE"))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/account/swagger/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(new RequestResponseLogFilter(), DisableEncodeUrlFilter.class)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/account/swagger/**").hasAuthority("ACCOUNT_SWAGGER_ACCESS")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
