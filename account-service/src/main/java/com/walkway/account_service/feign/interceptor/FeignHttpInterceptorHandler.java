package com.walkway.account_service.feign.interceptor;

import feign.RequestTemplate;

public interface FeignHttpInterceptorHandler {
    void apply(RequestTemplate template);
}
