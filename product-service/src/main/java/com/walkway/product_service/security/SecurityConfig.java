package com.walkway.product_service.security;

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
                .securityMatcher(PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.OPTIONS,"/api/product/**"))
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/product/**").permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain productImageFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/image/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/image").hasAuthority("CREATE_PRODUCT_IMAGE")
                        .requestMatchers(HttpMethod.DELETE, "/api/product/image/**").hasAuthority("DELETE_PRODUCT_IMAGE")
                        .requestMatchers(HttpMethod.GET, "/api/product/image/**").hasAuthority("READ_PRODUCT_IMAGE")
                )
                .oauth2ResourceServer(oauth2->oauth2
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain productGenderFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/gender/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/gender").hasAuthority("CREATE_PRODUCT_GENDER")
                        .requestMatchers(HttpMethod.PUT,"/api/product/gender/**").hasAuthority("UPDATE_PRODUCT_GENDER")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/gender/**").hasAuthority("DELETE_PRODUCT_GENDER")
                        .requestMatchers(HttpMethod.GET,"/api/product/gender/**").hasAuthority("READ_PRODUCT_GENDER")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain productColourFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/colour/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/colour").hasAuthority("CREATE_PRODUCT_COLOUR")
                        .requestMatchers(HttpMethod.PUT,"/api/product/colour/**").hasAuthority("UPDATE_PRODUCT_COLOUR")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/colour/**").hasAuthority("DELETE_PRODUCT_COLOUR")
                        .requestMatchers(HttpMethod.GET,"/api/product/colour/**").hasAuthority("READ_PRODUCT_COLOUR")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(5)
    public SecurityFilterChain productSizeFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/size/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/size").hasAuthority("CREATE_PRODUCT_SIZE")
                        .requestMatchers(HttpMethod.PUT,"/api/product/size/**").hasAuthority("UPDATE_PRODUCT_SIZE")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/size/**").hasAuthority("DELETE_PRODUCT_SIZE")
                        .requestMatchers(HttpMethod.GET,"/api/product/size/**").hasAuthority("READ_PRODUCT_SIZE")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(6)
    public SecurityFilterChain productCategoryFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/category/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/category").hasAuthority("CREATE_PRODUCT_CATEGORY")
                        .requestMatchers(HttpMethod.PUT,"/api/product/category/**").hasAuthority("UPDATE_PRODUCT_CATEGORY")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/category/**").hasAuthority("DELETE_PRODUCT_CATEGORY")
                        .requestMatchers(HttpMethod.GET,"/api/product/category/**").hasAuthority("READ_PRODUCT_CATEGORY")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(7)
    public SecurityFilterChain productFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/products/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/products").hasAuthority("CREATE_PRODUCT")
                        .requestMatchers(HttpMethod.PUT,"/api/product/products/**").hasAuthority("UPDATE_PRODUCT")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/products/**").hasAuthority("DELETE_PRODUCT")
                        .requestMatchers(HttpMethod.GET,"/api/product/products/**").hasAuthority("READ_PRODUCT")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(8)
    public SecurityFilterChain productItemFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/product-item/**")
                .cors(cors-> cors.configurationSource(new EdgeServiceCORSConfig(edgeServiceUrl)))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/api/product/product-item").hasAuthority("CREATE_PRODUCT_ITEM")
                        .requestMatchers(HttpMethod.PUT,"/api/product/product-item/**").hasAuthority("UPDATE_PRODUCT_ITEM")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/product-item/**").hasAuthority("DELETE_PRODUCT_ITEM")
                        .requestMatchers(HttpMethod.GET,"/api/product/product-item/**").hasAuthority("READ_PRODUCT_ITEM")
                )
                .oauth2ResourceServer(oauth2->oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    @Order(9)
    public SecurityFilterChain swaggerFilterChain (HttpSecurity http) throws Exception{
        http
                .securityMatcher("/api/product/swagger/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/product/swagger/**").hasAuthority("PRODUCT_SWAGGER_ACCESS")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
