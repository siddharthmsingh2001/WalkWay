package com.walkway.inventory_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkway.inventory_service.dto.ErrorResponseDto;
import com.walkway.inventory_service.exception.ResponseStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
/*
    ExceptionTranslationFilter. This filter sits in Spring Security's filter chain and is responsible for catching security-related exceptions and translating them into appropriate HTTP responses.
    The AuthenticationEntryPoint is responsible for initiating the authentication scheme when an unauthenticated user attempts to access a secured resource. In simpler terms, if Spring Security determines that a request needs authentication but no valid credentials (or any credentials at all) are provided, the AuthenticationEntryPoint is called.
    AuthenticationEntryPoint is invoked by the ExceptionTranslationFilter if: SecurityContextHolder is empty | AuthenticationException (such as BadCredentialsException, InsufficientAuthenticationException, UsernameNotFoundException, InvalidBearerTokenException, etc.) is thrown during the authentication process
    AuthenticationEntryPoint can then configure what response to send back to the caller or what to do. say redirects the user to a login page. (e.g., LoginUrlAuthenticationEntryPoint or just send back a response.
 */

public class BearerAuthenticationEntryPoint implements AuthenticationEntryPoint{

    private final AuthenticationEntryPoint delegate;
    private final ObjectMapper objectMapper;

    public BearerAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.delegate = new BearerTokenAuthenticationEntryPoint();
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.delegate.commence(request, response, authException);
        if(authException instanceof InvalidBearerTokenException validationException){
            var errorResponseDto = new ErrorResponseDto(request.getRequestURI(), ResponseStatus.BAD_JWT_EXCEPTION,validationException.getMessage());
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            objectMapper.writeValue(response.getWriter(), errorResponseDto);
        }
        if(authException instanceof InsufficientAuthenticationException validationException){
            var errorResponseDto = new ErrorResponseDto(request.getRequestURI(), ResponseStatus.INSUFFICIENT_AUTHENTICATION_EXCEPTION, validationException.getMessage());
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            objectMapper.writeValue(response.getWriter(), errorResponseDto);
        }
    }
}
