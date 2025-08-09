package com.walkway.account_service.security;

import com.walkway.account_service.dto.keycloak.client.KeycloakClientToken;
import com.walkway.account_service.feign.keycloak.KeycloakTokenClient;
import feign.FeignException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AccountClientAccessTokenManager {

    private final Object lock = new Object();
    private volatile String accessToken;
    private final KeycloakTokenClient keycloakTokenClient;
    private Instant expiresAt;
    private static final Duration EXPIRY_BUFFER = Duration.ofMinutes(20);

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String keycloakAccountClientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String keycloakAccountClientSecret;

    @PostConstruct
    private void init() throws Exception {
        refreshToken();
    }

    public String getAccessToken(){
        if(accessToken == null || isExpiringSoon()){
            synchronized (lock){
                if(accessToken == null || isExpiringSoon()){
                    refreshToken();
                }
            }
        }
        return accessToken;
    }

    private boolean isExpiringSoon(){
        return expiresAt == null || Instant.now().plus(EXPIRY_BUFFER).isAfter(expiresAt);
    }

    private String refreshToken() {
        try{
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("grant_type", "client_credentials");
            form.add("client_id", keycloakAccountClientId);
            form.add("client_secret", keycloakAccountClientSecret);
            KeycloakClientToken response = keycloakTokenClient.getClientToken(MediaType.APPLICATION_FORM_URLENCODED_VALUE,form);
            this.expiresAt = Instant.now().plusSeconds(response.getExpiresIn());
            this.accessToken = response.getAccessToken();
            return accessToken;
        } catch (FeignException e) {
            throw new RuntimeException(e);
        }
    }
}