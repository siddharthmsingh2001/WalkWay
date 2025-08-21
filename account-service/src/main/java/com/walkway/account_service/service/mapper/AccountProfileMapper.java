package com.walkway.account_service.service.mapper;

import com.walkway.account_service.dto.account.AccountProfileResponse;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import com.walkway.account_service.entity.AccountProfile;
import org.springframework.stereotype.Service;

@Service
public class AccountProfileMapper {

    public AccountProfile toAccountProfile(AccountProfile accountProfile, KeycloakUserRepresentation userRepresentation){
        return accountProfile
                .setProfileFirstName(userRepresentation.getFirstName())
                .setProfileLastName(userRepresentation.getLastName())
                .setProfileDob(null)
                .setProfileGender(null);
    }

    public AccountProfileResponse toAccountProfileResponse(AccountProfileResponse accountProfileResponse, AccountProfile accountProfile){
        return accountProfileResponse
                .setProfileId(accountProfile.getProfileId())
                .setProfileFirstName(accountProfile.getProfileFirstName())
                .setProfileLastName(accountProfile.getProfileLastName())
                .setProfileDob(accountProfile.getProfileDob())
                .setProfileGender(accountProfile.getProfileGender());
    }
}
