package com.walkway.account_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkway.account_service.dto.ErrorResponseDto;
import com.walkway.account_service.exception.ResponseStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;
/*
    AccessDeniedHandler is responsible for handling situations where an authenticated user attempts to access a resource for which they do not have sufficient permissions (authorization).
    If the JWT Token is valid Authentication Object is set, but it lacks required Authorities.
*/

public class AuthorizationDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AuthorizationDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(accessDeniedException instanceof AuthorizationDeniedException){
            var errorResponseDto = new ErrorResponseDto(request.getRequestURI(), ResponseStatus.AUTHORIZATION_DENIED_EXCEPTION,accessDeniedException.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), errorResponseDto);
        }
    }
}
