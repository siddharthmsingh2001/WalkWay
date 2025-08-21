package com.walkway.account_service.service.impl;

import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.*;
import com.walkway.account_service.service.OrchestrationService;
import com.walkway.account_service.service.AccountProfileService;
import com.walkway.account_service.service.AccountService;
import com.walkway.account_service.service.KeycloakAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestrationServiceImpl implements OrchestrationService {

    private final AccountService accountService;
    private final AccountProfileService accountProfileService;
    private final KeycloakAccountService keycloakAccountService;

    @Transactional
    public AccountResponse registerUser(RegistrationRequest registrationRequest) throws RealmAccountAlreadyExistException, InvalidRoleException, ServiceAccountAlreadyExistException, ServiceAccountNotFoundException, AccountProfileAlreadyExistException {
        try {
            KeycloakUserRepresentation userRepresentation = keycloakAccountService.createUserInRealm(registrationRequest);
            AccountResponse accountResponse = accountService.createAccount(userRepresentation);
            accountProfileService.createProfile(userRepresentation);
            return accountResponse;
        } catch (RealmAccountAlreadyExistException cause){
            throw new RealmAccountAlreadyExistException();
        } catch (InvalidRoleException cause){
            keycloakAccountService.deleteUserInRealm(registrationRequest.getAccountEmail());
            throw new InvalidRoleException();
        } catch (ServiceAccountAlreadyExistException cause){
            accountService.deleteAccount(registrationRequest.getAccountEmail());
            keycloakAccountService.deleteUserInRealm(registrationRequest.getAccountEmail());
            throw new ServiceAccountAlreadyExistException();
        } catch (AccountProfileAlreadyExistException cause) {
            accountService.deleteAccount(registrationRequest.getAccountEmail());
            keycloakAccountService.deleteUserInRealm(registrationRequest.getAccountEmail());
            throw new AccountProfileAlreadyExistException(cause);
        }
    }
}
