package com.walkway.account_service.service;

import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.InvalidRoleException;
import com.walkway.account_service.exception.custom.RealmAccountAlreadyExistException;

public interface KeycloakAccountService {

     KeycloakUserRepresentation createUserInRealm(RegistrationRequest request) throws InvalidRoleException, RealmAccountAlreadyExistException;

     void deleteUserInRealm(String email);
}
