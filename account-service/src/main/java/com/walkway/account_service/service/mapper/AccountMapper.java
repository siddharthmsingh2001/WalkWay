package com.walkway.account_service.service.mapper;

import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.entity.Account;
import com.walkway.account_service.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountMapper {

    public Account toAccount(Account account, KeycloakUserRepresentation userRepresentation){
        return account
                .setAccountId(UUID.fromString(userRepresentation.getId()))
                .setAccountEmail(userRepresentation.getEmail())
                .setAccountRole(Role.valueOf(userRepresentation.getRealmRoles().get(0).getName()))
                .setAccountPhone(userRepresentation.getAttributes().get("phone").get(0));
    }

    public AccountResponse toAccountResponse(AccountResponse accountResponse, Account account) {
        return accountResponse
                .setAccountId(account.getAccountId())
                .setAccountEmail(account.getAccountEmail())
                .setAccountRole(account.getAccountRole())
                .setAccountPhone(account.getAccountPhone());

    }

    public List<AccountResponse> toAccountDtoList(List<Account> accountList){
        return accountList.stream()
                .map(account -> toAccountResponse(new AccountResponse(), account))
                .toList();
    }
}
