package com.walkway.account_service.feign.handler.custom;

import com.walkway.account_service.feign.handler.FeignHttpExceptionHandler;
import com.walkway.account_service.feign.handler.FeignUtils;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GeneralHandler implements FeignHttpExceptionHandler {

    @Override
    public Exception handle(Response response) {
        String body = FeignUtils.readBody(response.body());
        log.error("{}: status -> {}",this.getClass().getName(),response.status());
        log.error("{}: request -> {}",this.getClass().getName(),response.request());
        log.error("{}: headers -> {}",this.getClass().getName(),response.headers());
        log.error("{}: body -> {}",this.getClass().getName(),response.body());
        return new Exception(body);

    }
}
