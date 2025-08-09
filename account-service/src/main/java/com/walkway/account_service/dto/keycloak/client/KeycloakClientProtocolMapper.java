package com.walkway.account_service.dto.keycloak.client;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class KeycloakClientProtocolMapper {
    private String id;
    private String name;
    private String protocol;
    private String protocolMapper;
    private Boolean consentRequired;
    private Map<String, String> config;
}
