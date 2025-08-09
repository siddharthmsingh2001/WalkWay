package com.walkway.edge_service.security;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// CONFIGURATION WE ONLY NEED WHEN WE HAVE THE FRONTEND WORKING
public class FrontendCORSConfig implements CorsConfigurationSource {

    private final String frontendUrl;

    public FrontendCORSConfig(String frontendUrl){
        this.frontendUrl = frontendUrl;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of(frontendUrl));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        return corsConfiguration;
    }
}
