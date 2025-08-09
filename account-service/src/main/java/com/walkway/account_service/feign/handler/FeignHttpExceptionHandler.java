package com.walkway.account_service.feign.handler;

import feign.Response;

/**
 * The purpose of this interface is to generalize the handling of the Exception that occurs in the FeignClient.
 * All the Handlers must have a handle method which has a Feign.Response parameter to which will pass the fail Http Response and the handler must return an appropriate Exception based on how the returned Response is.
 * Sat based on Response.status() or Response.body() etc...
 */
public interface FeignHttpExceptionHandler {
    Exception handle(Response response);
}
