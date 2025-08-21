package com.walkway.account_service.feign.keycloak;

import com.walkway.account_service.dto.keycloak.client.KeycloakClientToken;
import com.walkway.account_service.feign.handler.custom.GeneralHandler;
import com.walkway.account_service.feign.handler.HandleFeignError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "keycloak-token-client", url = "${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
public interface KeycloakTokenClient {

    @HandleFeignError(GeneralHandler.class)
    @PostMapping(value = "/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KeycloakClientToken getClientToken(
            @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType,
            @RequestBody MultiValueMap<String, String> body
    );
}