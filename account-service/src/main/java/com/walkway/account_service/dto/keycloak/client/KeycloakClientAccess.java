package com.walkway.account_service.dto.keycloak.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class KeycloakClientAccess {
    private Boolean view;
    private Boolean configure;
    private Boolean manage;

    @Override
    public String toString() {
        return "KeycloakClientAccess {\n" +
                "  view = " + view + ",\n" +
                "  configure = " + configure + ",\n" +
                "  manage = " + manage + "\n" +
                "}";
    }
}
