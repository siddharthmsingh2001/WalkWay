package com.walkway.account_service.service;


import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.account.*;
import com.walkway.account_service.exception.custom.*;

public interface OrchestrationService {
    AccountResponse registerUser(RegistrationRequest registrationRequest) throws ServiceAccountAlreadyExistException, AccountProfileAlreadyExistException, ServiceAccountNotFoundException, InvalidRoleException, RealmAccountAlreadyExistException;
}
