package com.walkway.inventory_service.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EdgeServiceCORSConfig implements CorsConfigurationSource {
    private final String edgeServiceUrl;

    public EdgeServiceCORSConfig(String edgeServiceUrl){
        this.edgeServiceUrl = edgeServiceUrl;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        String originHeader = request.getHeader("Origin");

        // This is present if a call is made by frontend or directly via the browser to edge-service. The origin header is not attached in such cases
        if(originHeader == null){
            return null;
        }

        // This is the case for when the swagger-ui request is made via the edge-service...
        if(originHeader.equals(edgeServiceUrl)){
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedOrigins(List.of(edgeServiceUrl));
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
            return corsConfiguration;
        }
        return null;
    }
}
