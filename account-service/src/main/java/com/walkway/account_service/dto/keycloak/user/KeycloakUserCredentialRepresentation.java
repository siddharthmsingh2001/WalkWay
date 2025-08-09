package com.walkway.account_service.dto.keycloak.user;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class KeycloakUserCredentialRepresentation {
    private final String type = "password";
    private String value;
    private final boolean temporary = false;
}
