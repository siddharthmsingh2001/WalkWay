package com.walkway.account_service.controller;

import com.walkway.account_service.dto.account.AccountProfileResponse;
import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.exception.custom.AccountProfileNotFoundException;
import com.walkway.account_service.exception.custom.ServiceAccountNotFoundException;
import com.walkway.account_service.service.AccountProfileService;
import com.walkway.account_service.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(
        name ="User Read Update Delete Operations",
        description = "User Read Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private final AccountService accountService;
    private final AccountProfileService accountProfileService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND")
            }
    )
    @Operation(
            summary = "Account Details",
            description = "Retrieves core user details based on the SecurityContext",
            security = @SecurityRequirement(name = "account-service-pkce"))
    @GetMapping("/account")
    public ResponseEntity<AccountResponse> getMyAccount(
        @AuthenticationPrincipal Jwt jwt
    ) throws ServiceAccountNotFoundException {
        UUID accountId = UUID.fromString(jwt.getSubject());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findById(accountId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND")
            }
    )
    @Operation(
            summary = "Account Profile Details",
            description = "Retrieves user meta details based on the SecurityContext",
            security = @SecurityRequirement(name = "account-service-pkce"))
    @GetMapping("/profile")
    public ResponseEntity<AccountProfileResponse> getMyProfile(
            @AuthenticationPrincipal Jwt jwt
    ) throws AccountProfileNotFoundException, ServiceAccountNotFoundException {
        UUID accountId = UUID.fromString(jwt.getSubject());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountProfileService.findByAccountId(accountId));
    }
}
