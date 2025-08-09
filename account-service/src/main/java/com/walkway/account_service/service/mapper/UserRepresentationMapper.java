package com.walkway.account_service.service.mapper;

import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserCredentialRepresentation;
import com.walkway.account_service.dto.keycloak.user.KeycloakUserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UserRepresentationMapper {

    public KeycloakUserRepresentation toUserRepresentation(RegistrationRequest request){
        return KeycloakUserRepresentation.builder()
                .username(request.getAccountEmail())
                .email(request.getAccountEmail())
                .enabled(true)
                .emailVerified(false)
                .attributes(new HashMap<>(){{
                    put("phone", List.of(request.getAccountPhone()));
                }})
                .credentials(
                        Collections.singletonList(
                                KeycloakUserCredentialRepresentation.builder()
                                        .value(request.getAccountPassword())
                                        .build()
                        )
                )
                .firstName(request.getAccountFirstName())
                .lastName(request.getAccountLastName())
                .requiredActions(Collections.singletonList(
                        "VERIFY_EMAIL"
                )).build();
    }
}
