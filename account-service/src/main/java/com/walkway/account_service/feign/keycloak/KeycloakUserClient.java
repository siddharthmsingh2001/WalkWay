package com.walkway.account_service.feign.keycloak;

import com.walkway.account_service.dto.keycloak.KeycloakRealmRole;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRole;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.feign.handler.HandleFeignError;
import com.walkway.account_service.feign.handler.custom.DuplicateRealmAccountHandler;
import com.walkway.account_service.feign.handler.custom.GeneralHandler;
import com.walkway.account_service.feign.interceptor.AddFeignInterceptor;
import com.walkway.account_service.feign.interceptor.custom.KeycloakClientAccessTokenInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "keycloak-user-client", url = "${application.iam.keycloak.realm.admin.uri}")
public interface KeycloakUserClient {

    @HandleFeignError(DuplicateRealmAccountHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @PostMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(
            @RequestBody KeycloakUserRepresentation user
    );

    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @DeleteMapping("/users/{id}")
    void deleteUser(
            @PathVariable("id") String id
    );

    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<KeycloakUserRepresentation> getUserByEmail(
            @RequestParam("email") String email
    );

    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @PostMapping(value = "/users/{userId}/role-mappings/realm",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void assignRealmRoleToUser(
            @PathVariable("userId") String userId,
            @RequestBody List<KeycloakRealmRole> roles
    );

    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @PostMapping("/users/{userId}/role-mappings/clients/{clientId}")
    void assignClientRoleToUser(
        @PathVariable("userId") String userId,
        @PathVariable("clientId") String clientId,
        @RequestBody List<KeycloakClientRole> roles
    );
}