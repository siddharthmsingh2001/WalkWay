package com.walkway.account_service.dto.keycloak.user;

import com.walkway.account_service.dto.keycloak.KeycloakRealmRole;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRole;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class KeycloakUserRepresentation {
    private String id;
    private String username;
    private String email;
    private boolean enabled;
    private boolean emailVerified;
    private Map<String, List<String>> attributes;
    private List<KeycloakUserCredentialRepresentation> credentials;
    private List<KeycloakRealmRole> realmRoles;
    private Map<String, List<KeycloakClientRole>> clientRoles;
    private List<String> requiredActions;
    private String firstName;
    private String lastName;
}