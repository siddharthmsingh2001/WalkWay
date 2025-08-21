package com.walkway.account_service.beans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Account Service API",
                version = "0.1",
                description = "API for managing user accounts",
                contact = @Contact(
                        name = "Siddharth",
                        email = "siddhartmsingh2001@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8083", description = "Local Development Server")
        }
)
@SecurityScheme(
        name = "account-service-pkce",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "http://localhost:8081/realms/walkway/protocol/openid-connect/auth",
                        tokenUrl = "http://localhost:8081/realms/walkway/protocol/openid-connect/token"
                )
        )
)
public class OpenAPI3Config {
}
