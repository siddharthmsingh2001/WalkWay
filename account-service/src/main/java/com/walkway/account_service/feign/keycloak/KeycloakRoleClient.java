package com.walkway.account_service.feign.keycloak;

import com.walkway.account_service.dto.keycloak.KeycloakRealmRole;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRepresentation;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRole;
import com.walkway.account_service.entity.Role;
import com.walkway.account_service.feign.handler.HandleFeignError;
import com.walkway.account_service.feign.handler.custom.GeneralHandler;
import com.walkway.account_service.feign.interceptor.AddFeignInterceptor;
import com.walkway.account_service.feign.interceptor.custom.KeycloakClientAccessTokenInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "keycloak-role-client", url = "${application.iam.keycloak.realm.admin.uri}")
public interface KeycloakRoleClient {

    /**
     *
     * Retrieves the specific Client based on clientId(String) provided.
     *
     * @param clientId : The clientId (!client - id(UUID)) of the microservice.
     * @return : The return will always be a list of ClientRepresentation even if it contains only one entry.
     */
    @GetMapping("/clients?clientId={clientId}")
    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    List<KeycloakClientRepresentation> getClient(
            @RequestParam String clientId
    );


    /**
     * Retrieves the specific Realm Role based on role name provided.
     *
     * @param role : The role here is the name of the role
     * @return : The return type will always be a single Realm Role representation of the provided role name.
     */
    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @GetMapping("/roles/{role}")
    KeycloakRealmRole getRealmRole(
            @PathVariable("role") Role role
    );

    /**
     * Retrieves all the ClientRoles from all the clients that are composite of the Realm Role.
     *
     * @param role : The role here is the name of the role
     * @return : The return type will be list of all ClientRoles related to the Role
     */
    @HandleFeignError(GeneralHandler.class)
    @AddFeignInterceptor(KeycloakClientAccessTokenInterceptor.class)
    @GetMapping("/roles/{role}/composites")
    List<KeycloakClientRole> getClientRolesForRole(
            @PathVariable("role") String role
    );
}
