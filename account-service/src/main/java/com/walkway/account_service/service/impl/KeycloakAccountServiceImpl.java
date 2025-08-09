package com.walkway.account_service.service.impl;

import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.dto.keycloak.client.KeycloakClientRole;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.InvalidRoleException;
import com.walkway.account_service.exception.custom.RealmAccountAlreadyExistException;
import com.walkway.account_service.feign.keycloak.KeycloakUserClient;
import com.walkway.account_service.security.RealmRoleMapping;
import com.walkway.account_service.service.KeycloakAccountService;
import com.walkway.account_service.service.mapper.UserRepresentationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakAccountServiceImpl implements KeycloakAccountService {

    private final KeycloakUserClient keycloakUserClient;
    private final UserRepresentationMapper userRepresentationMapper;
    private final RealmRoleMapping realmRoleMapping;

    public KeycloakUserRepresentation createUserInRealm(RegistrationRequest request) throws InvalidRoleException, RealmAccountAlreadyExistException {
        KeycloakUserRepresentation userRepresentation;
        userRepresentation = createUser(request);
        userRepresentation = assignRoleToUser(userRepresentation, request);
        return userRepresentation;
    }

    public void deleteUserInRealm(String email){
        keycloakUserClient.deleteUser(
                findUserInKeycloak(email).getId()
        );
    }

    private KeycloakUserRepresentation createUser(RegistrationRequest request){
        keycloakUserClient.createUser(userRepresentationMapper.toUserRepresentation(request));
        return findUserInKeycloak(request.getAccountEmail());
    }

    private KeycloakUserRepresentation findUserInKeycloak(String accountEmail){
        return keycloakUserClient.getUserByEmail(accountEmail).get(0);
    }

    private KeycloakUserRepresentation assignRoleToUser(KeycloakUserRepresentation userRepresentation, RegistrationRequest request) throws InvalidRoleException {
        userRepresentation.setRealmRoles(List.of(realmRoleMapping.getRealmRole(request.getAccountRole())));
        keycloakUserClient.assignRealmRoleToUser(userRepresentation.getId(), userRepresentation.getRealmRoles());
        userRepresentation.setClientRoles(realmRoleMapping.getClientRoles(request.getAccountRole()));
        for(Map.Entry<String, List<KeycloakClientRole>> entry:userRepresentation.getClientRoles().entrySet()){
            keycloakUserClient.assignClientRoleToUser(userRepresentation.getId(), entry.getKey(), entry.getValue());
        }
        return userRepresentation;
    }
}
