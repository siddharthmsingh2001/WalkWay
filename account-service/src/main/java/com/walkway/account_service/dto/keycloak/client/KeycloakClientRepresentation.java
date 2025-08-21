package com.walkway.account_service.dto.keycloak.client;

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
public class KeycloakClientRepresentation {
    private String id;
    private String clientId;
    private String name;
    private String description;
    private String rootUrl;
    private String baseUrl;
    private String adminUrl;
    private Boolean surrogateAuthRequired;
    private Boolean enabled;
    private Boolean alwaysDisplayInConsole;
    private String clientAuthenticatorType;
    private List<String> redirectUris;
    private List<String> webOrigins;
    private Integer notBefore;
    private Boolean bearerOnly;
    private Boolean consentRequired;
    private Boolean standardFlowEnabled;
    private Boolean implicitFlowEnabled;
    private Boolean directAccessGrantsEnabled;
    private Boolean serviceAccountsEnabled;
    private Boolean publicClient;
    private Boolean frontchannelLogout;
    private String protocol;
    private Map<String, String> attributes;
    private Map<String, String> authenticationFlowBindingOverrides;
    private Boolean fullScopeAllowed;
    private Integer nodeReRegistrationTimeout;
    private List<String> defaultClientScopes;
    private List<String> optionalClientScopes;
    private KeycloakClientAccess access;
    private List<KeycloakClientProtocolMapper> protocolMappers;
}
