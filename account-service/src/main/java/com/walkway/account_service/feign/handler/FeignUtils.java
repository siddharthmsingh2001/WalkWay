package com.walkway.account_service.feign.handler;

import feign.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FeignUtils {

    public static String readBody(Response.Body body){
        if(body == null) return "";
        try(Reader reader = body.asReader(StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException cause){
            return "Unable to read body: " + cause.getMessage();
        }
    }
}
