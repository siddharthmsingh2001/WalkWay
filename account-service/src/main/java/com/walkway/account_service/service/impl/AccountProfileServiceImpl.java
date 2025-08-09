package com.walkway.account_service.service.impl;

import com.walkway.account_service.dto.account.AccountProfileResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;
import com.walkway.account_service.exception.custom.AccountProfileAlreadyExistException;
import com.walkway.account_service.exception.custom.AccountProfileNotFoundException;
import com.walkway.account_service.entity.Account;
import com.walkway.account_service.entity.AccountProfile;
import com.walkway.account_service.repo.AccountProfileRepository;
import com.walkway.account_service.repo.AccountRepository;
import com.walkway.account_service.service.AccountProfileService;
import com.walkway.account_service.service.mapper.AccountProfileMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountProfileServiceImpl implements AccountProfileService {

    private final AccountProfileRepository accountProfileRepository;
    private final AccountRepository accountRepository;
    private final AccountProfileMapper accountProfileMapper;

    @Transactional
    public AccountProfileResponse createProfile(KeycloakUserRepresentation userRepresentation) throws ServiceAccountNotFoundException, AccountProfileAlreadyExistException {
        Account account = accountRepository.findById(UUID.fromString(userRepresentation.getId())).orElseThrow(ServiceAccountNotFoundException::new);
        Optional<AccountProfile> accPro = accountProfileRepository.findByAccountId(account);
        if(accPro.isPresent()){
            throw new AccountProfileAlreadyExistException();
        }
        AccountProfile accountProfile = accountProfileMapper.toAccountProfile(new AccountProfile(), userRepresentation);
        accountProfile.setAccountId(account);
        AccountProfile savedProfile = accountProfileRepository.save(accountProfile);
        return accountProfileMapper.toAccountProfileResponse(new AccountProfileResponse(), savedProfile);
    }

    public AccountProfileResponse findByAccountId(UUID accountId) throws ServiceAccountNotFoundException, AccountProfileNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(ServiceAccountNotFoundException::new);
        AccountProfile accountProfile =  accountProfileRepository.findByAccountId(account).orElseThrow(AccountProfileNotFoundException::new);
        return  accountProfileMapper.toAccountProfileResponse(new AccountProfileResponse(), accountProfile);
    }

    @Transactional
    public void deleteProfile(UUID profileId){
        accountProfileRepository.deleteById(profileId);
    }
}
