package com.walkway.account_service.dto.keycloak.client;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class KeycloakClientRole {

    private String id;
    private String name;
    private String description;
    private boolean composite;
    private boolean clientRole;
    private String containerId;

    @Override
    public String toString() {
        return "KeycloakClientRole {\n" +
                "  id = '" + id + "',\n" +
                "  name = '" + name + "',\n" +
                "  description = '" + description + "',\n" +
                "  composite = " + composite + ",\n" +
                "  clientRole = " + clientRole + ",\n" +
                "  containerId = '" + containerId + "'\n" +
                "}";
    }
}
