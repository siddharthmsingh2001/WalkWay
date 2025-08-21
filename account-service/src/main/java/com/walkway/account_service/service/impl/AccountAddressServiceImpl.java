package com.walkway.account_service.service.impl;

import com.walkway.account_service.dto.account.AccountAddressResponse;
import com.walkway.account_service.dto.account.AddressCreateRequest;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;
import com.walkway.account_service.entity.Account;
import com.walkway.account_service.entity.AccountAddress;
import com.walkway.account_service.repo.AccountAddressRepository;
import com.walkway.account_service.repo.AccountRepository;
import com.walkway.account_service.service.mapper.AccountAddressMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountAddressServiceImpl {

    private final AccountRepository accountRepository;
    private final AccountAddressRepository accountAddressRepository;
    private final AccountAddressMapper accountAddressMapper;

    @Transactional
    public void createAddress(AddressCreateRequest request, UUID accountId) throws ServiceAccountNotFoundException {
        Optional<Account> acc = accountRepository.findById(accountId);
        if(acc.isEmpty()){
            throw new ServiceAccountNotFoundException();
        }
        AccountAddress accountAddress = accountAddressMapper.toAccountAddress(new AccountAddress(), request);
        accountAddress.setAccountId(acc.get());
        accountAddressRepository.save(accountAddress);
    }

    @Transactional
    public List<AccountAddressResponse> getAllAddress(UUID accountId) throws ServiceAccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(ServiceAccountNotFoundException::new);
        List<AccountAddress> addresses = accountAddressRepository.findByAccountId(account);
        return accountAddressMapper.toAccountAddressDtoList(addresses);
    }
}
