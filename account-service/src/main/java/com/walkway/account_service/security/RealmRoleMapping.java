package com.walkway.account_service.security;

import com.walkway.account_service.dto.keycloak.KeycloakRealmRole;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRepresentation;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRole;
import com.walkway.account_service.entity.Role;
import com.walkway.account_service.exception.custom.InvalidRoleException;
import com.walkway.account_service.feign.keycloak.KeycloakRoleClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RealmRoleMapping implements ApplicationListener<ApplicationReadyEvent> {

    private final KeycloakRoleClient keycloakRoleClient;
    private static final Role[] BUSINESS_REALM_ROLE = Role.values();
    private final Map<Role, KeycloakRealmRole> realmRoleMap = new EnumMap<>(Role.class);
    private final Map<String, String> clientNameUUIDMap = new HashMap<>();
    private final Map<Role, Map<String, List<KeycloakClientRole>>> clientRoleMap = new EnumMap<>(Role.class);
    private static final List<String> BUSINESS_CLIENT_IDS = List.of(
            "account-service", "inventory-service", "product-service"
    );

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadRealmRoleMap();
        loadClientNameUUIDMap();
        loadClientRoleMap();
    }

    private void loadRealmRoleMap(){
        for(Role role: BUSINESS_REALM_ROLE){
            KeycloakRealmRole realmRole = keycloakRoleClient.getRealmRole(role);
            if(realmRole!=null){
                realmRoleMap.put(role, realmRole);
            } else{
                log.warn("{} -> Realm Role not found in Realm: {}",this.getClass().getName(), role);
            }
        }
    }

    private void loadClientNameUUIDMap(){
        for(String clientId: BUSINESS_CLIENT_IDS){
            List<KeycloakClientRepresentation> representationList = keycloakRoleClient.getClient(clientId);
            if(!representationList.isEmpty()){
                KeycloakClientRepresentation client = representationList.get(0);
                clientNameUUIDMap.put(client.getClientId(), client.getId());
            } else{
                log.warn("{} -> Client not found in Realm: {}",this.getClass().getName(), clientId);
            }
        }
    }

    private void loadClientRoleMap(){
        for(Role realmRole: Role.values()){
            List<KeycloakClientRole> allClientRoles = keycloakRoleClient.getClientRolesForRole(realmRole.name());
            Map<String, List<KeycloakClientRole>> clientRole = new HashMap<>();
            for(KeycloakClientRole keycloakClientRole: allClientRoles){
                if(clientRole.containsKey(keycloakClientRole.getContainerId())){
                    List<KeycloakClientRole> roleList = clientRole.get(keycloakClientRole.getContainerId());
                    roleList.add(keycloakClientRole);
                    clientRole.put(keycloakClientRole.getContainerId(), roleList);
                } else{
                    clientRole.put(keycloakClientRole.getContainerId(), new ArrayList<>(List.of(keycloakClientRole)));
                }
            }
            clientRoleMap.put(realmRole,clientRole);
        }
    }

    public KeycloakRealmRole getRealmRole(Role role) throws InvalidRoleException {
        if(realmRoleMap.containsKey(role)){
            return realmRoleMap.get(role);
        }
        throw new InvalidRoleException();
    }

    public Map<String, List<KeycloakClientRole>> getClientRoles(Role role) throws InvalidRoleException {
        if(clientRoleMap.containsKey(role)){
            return clientRoleMap.get(role);
        }
        throw new InvalidRoleException();
    }
}
