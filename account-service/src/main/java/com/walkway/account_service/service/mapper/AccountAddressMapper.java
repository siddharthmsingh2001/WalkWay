package com.walkway.account_service.service.mapper;

import com.walkway.account_service.dto.account.AccountAddressResponse;
import com.walkway.account_service.dto.account.AddressCreateRequest;
import com.walkway.account_service.entity.AccountAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAddressMapper {

    public AccountAddress toAccountAddress(AccountAddress accountAddress, AddressCreateRequest request){
        return accountAddress
                .setAddressLine1(request.getAddressLine1())
                .setAddressLine2(request.getAddressLine2())
                .setAddressCity(request.getAddressCity())
                .setAddressState(request.getAddressState())
                .setAddressZipcode(request.getAddressZipcode())
                .setAddressCountry(request.getAddressCountry());
    }

    public AccountAddressResponse toAccountAddressResponse(AccountAddressResponse accountAddressResponse, AccountAddress accountAddress){
        return accountAddressResponse
                .setAddressId(accountAddressResponse.getAddressId())
                .setAddressLine1(accountAddress.getAddressLine1())
                .setAddressLine2(accountAddress.getAddressLine2())
                .setAddressCity(accountAddress.getAddressCity())
                .setAddressState(accountAddress.getAddressState())
                .setAddressZipcode(accountAddress.getAddressZipcode())
                .setAddressCountry(accountAddress.getAddressCountry());
    }

    public List<AccountAddressResponse> toAccountAddressDtoList(List<AccountAddress> addresses) {
        return addresses.stream()
                .map(address -> toAccountAddressResponse(new AccountAddressResponse(), address))
                .toList();
    }
}
