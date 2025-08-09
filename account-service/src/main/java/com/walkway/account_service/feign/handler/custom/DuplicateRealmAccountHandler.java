package com.walkway.account_service.feign.handler.custom;

import com.walkway.account_service.exception.custom.RealmAccountAlreadyExistException;
import com.walkway.account_service.feign.handler.FeignHttpExceptionHandler;
import com.walkway.account_service.feign.handler.FeignUtils;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DuplicateRealmAccountHandler implements FeignHttpExceptionHandler {
    @Override
    public Exception handle(Response response) {
        String body = FeignUtils.readBody(response.body());
        switch (HttpStatus.resolve(response.status())){
            case CONFLICT:
                return new RealmAccountAlreadyExistException(body);
        }
        return new Exception(body);
    }
}
