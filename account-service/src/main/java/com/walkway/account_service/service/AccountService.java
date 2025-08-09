package com.walkway.account_service.service;

import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.ServiceAccountAlreadyExistException;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponse createAccount(KeycloakUserRepresentation userRepresentation) throws ServiceAccountAlreadyExistException;

    AccountResponse findById(UUID accountId) throws ServiceAccountNotFoundException;

    AccountResponse findByEmail(String accountEmail) throws ServiceAccountNotFoundException;

    UUID findAccountIdByEmail(String accountEmail) throws ServiceAccountNotFoundException;

    List<AccountResponse> getAllAccount();

    void deleteAccount(UUID accountId) throws ServiceAccountNotFoundException;

    void deleteAccount(String accountEmail) throws ServiceAccountNotFoundException;

}
