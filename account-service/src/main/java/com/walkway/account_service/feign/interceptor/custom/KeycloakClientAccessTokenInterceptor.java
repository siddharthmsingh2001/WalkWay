package com.walkway.account_service.feign.interceptor.custom;

import com.walkway.account_service.feign.interceptor.FeignHttpInterceptorHandler;
import com.walkway.account_service.security.AccountClientAccessTokenManager;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakClientAccessTokenInterceptor implements FeignHttpInterceptorHandler {

    private final AccountClientAccessTokenManager tokenManager;

    @Override
    public void apply(RequestTemplate template) {
        String accessToken = tokenManager.getAccessToken();
        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }
}
