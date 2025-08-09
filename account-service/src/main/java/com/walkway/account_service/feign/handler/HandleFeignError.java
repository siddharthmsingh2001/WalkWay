package com.walkway.account_service.feign.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Creating this annotation allows up to say: "If this method fails, handle it with this logic"
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface HandleFeignError {

    Class<? extends FeignHttpExceptionHandler> value();

}
