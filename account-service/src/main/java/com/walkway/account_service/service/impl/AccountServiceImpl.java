package com.walkway.account_service.service.impl;

import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.exception.custom.ServiceAccountAlreadyExistException;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;
import com.walkway.account_service.entity.Account;
import com.walkway.account_service.repo.AccountRepository;
import com.walkway.account_service.service.AccountService;
import com.walkway.account_service.service.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountResponse createAccount(KeycloakUserRepresentation userRepresentation) throws ServiceAccountAlreadyExistException {
        Optional<Account> acc = accountRepository.findByAccountEmail(userRepresentation.getEmail());
        if(acc.isPresent()){
            throw new ServiceAccountAlreadyExistException();
        } else{
            Account account = accountMapper.toAccount(new Account(),userRepresentation);
            Account savedAccount = accountRepository.save(account);
            return accountMapper.toAccountResponse(new AccountResponse(), savedAccount);
        }
    }

    public AccountResponse findById(UUID accountId) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(ServiceAccountNotFoundException::new);
        return accountMapper.toAccountResponse(new AccountResponse(), account);
    }

    public AccountResponse findByEmail(String accountEmail) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findByAccountEmail(accountEmail).orElseThrow(ServiceAccountNotFoundException::new);
        return accountMapper.toAccountResponse(new AccountResponse(), account);
    }

    public UUID findAccountIdByEmail(String accountEmail) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findByAccountEmail(accountEmail).orElseThrow(ServiceAccountNotFoundException::new);
        return account.getAccountId();
    }

    public List<AccountResponse> getAllAccount(){
        List<Account> allAccounts = accountRepository.findAll();
        return accountMapper.toAccountDtoList(allAccounts);
    }

    @Transactional
    public void deleteAccount(UUID accountId) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(ServiceAccountNotFoundException::new);
        accountRepository.delete(account);
    }

    @Transactional
    public void deleteAccount(String accountEmail) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findByAccountEmail(accountEmail)
                .orElseThrow(ServiceAccountNotFoundException::new);
        accountRepository.delete(account);
    }
}
