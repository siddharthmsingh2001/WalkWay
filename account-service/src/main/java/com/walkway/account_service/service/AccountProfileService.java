package com.walkway.account_service.service;

import com.walkway.account_service.dto.account.AccountProfileResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;
import com.walkway.account_service.exception.custom.AccountProfileAlreadyExistException;
import com.walkway.account_service.exception.custom.AccountProfileNotFoundException;

import java.util.UUID;

public interface AccountProfileService {
    AccountProfileResponse createProfile(KeycloakUserRepresentation userRepresentation) throws ServiceAccountNotFoundException, AccountProfileAlreadyExistException;

    AccountProfileResponse findByAccountId(UUID accountId) throws ServiceAccountNotFoundException, AccountProfileNotFoundException;

    void deleteProfile(UUID profileId);
}
