package com.walkway.inventory_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

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
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain swaggerAuthorizeFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher(PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.OPTIONS,"/api/inventory/**"))
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/inventory/**").permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain locationFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/inventory/location/**")
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/api/inventory/location").hasAuthority("CREATE_INVENTORY_LOCATION")
                        .requestMatchers(HttpMethod.GET,"/api/inventory/location/**").hasAuthority("READ_INVENTORY_LOCATION")
                        .requestMatchers(HttpMethod.PUT,"/api/inventory/location/**").hasAuthority("UPDATE_INVENTORY_LOCATION")
                        .requestMatchers(HttpMethod.DELETE,"/api/inventory/location/**").hasAuthority("DELETE_INVENTORY_LOCATION")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain productSnapshotFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/inventory/product-snapshot/**")
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/api/inventory/product-snapshot").hasAuthority("CREATE_INVENTORY_PRODUCT_SNAPSHOT")
                        .requestMatchers(HttpMethod.GET,"/api/inventory/product-snapshot/**").hasAuthority("READ_INVENTORY_PRODUCT_SNAPSHOT")
                        .requestMatchers(HttpMethod.PUT,"/api/inventory/product-snapshot/**").hasAuthority("UPDATE_INVENTORY_PRODUCT_SNAPSHOT")
                        .requestMatchers(HttpMethod.DELETE,"/api/inventory/product-snapshot/**").hasAuthority("DELETE_INVENTORY_PRODUCT_SNAPSHOT")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain stockFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/inventory/stock/**")
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/api/inventory/stock").hasAuthority("CREATE_INVENTORY_STOCK")
                        .requestMatchers(HttpMethod.GET,"/api/inventory/stock/**").hasAuthority("READ_INVENTORY_STOCK")
                        .requestMatchers(HttpMethod.PUT,"/api/inventory/stock/**").hasAuthority("UPDATE_INVENTORY_STOCK")
                        .requestMatchers(HttpMethod.DELETE,"/api/inventory/stock/**").hasAuthority("DELETE_INVENTORY_STOCK")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(5)
    public SecurityFilterChain warehouseFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/inventory/warehouse/**")
                .cors(cors -> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/api/inventory/warehouse").hasAuthority("CREATE_INVENTORY_WAREHOUSE")
                        .requestMatchers(HttpMethod.GET,"/api/inventory/warehouse/**").hasAuthority("READ_INVENTORY_WAREHOUSE")
                        .requestMatchers(HttpMethod.PUT,"/api/inventory/warehouse/**").hasAuthority("UPDATE_INVENTORY_WAREHOUSE")
                        .requestMatchers(HttpMethod.DELETE,"/api/inventory/warehouse/**").hasAuthority("DELETE_INVENTORY_WAREHOUSE")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(6)
    public SecurityFilterChain swaggerFilterChain (HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/inventory/swagger/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/inventory/swagger/**").hasAuthority("INVENTORY_SWAGGER_ACCESS")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
