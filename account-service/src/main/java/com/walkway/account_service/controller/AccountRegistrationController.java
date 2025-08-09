package com.walkway.account_service.controller;

import com.walkway.account_service.dto.RegistrationRequest;
import com.walkway.account_service.dto.account.AccountResponse;
import com.walkway.account_service.service.OrchestrationService;
import com.walkway.account_service.service.KeycloakAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name ="User Create Operations",
        description = "User Registration Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountRegistrationController {

    private final OrchestrationService orchestrationService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND")
            }
    )
    @Operation(
            summary = "User Registration",
            description = "Registers the Users to the Realm as well as the Service")
    @PostMapping("/register")
    public ResponseEntity<AccountResponse> register(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orchestrationService.registerUser(registrationRequest));
    }
}
