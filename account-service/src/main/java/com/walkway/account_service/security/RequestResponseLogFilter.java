package com.walkway.account_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;

public class RequestResponseLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long duration = System.currentTimeMillis() - startTime;

        logRequest(requestWrapper);
        logResponse(responseWrapper, duration);

        responseWrapper.copyBodyToResponse(); // Important to let response flow to client
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n====== Incoming Request ======\n");
        sb.append(request.getMethod()).append(" ").append(request.getRequestURI());

        String query = request.getQueryString();
        if (query != null) sb.append("?").append(query);
        sb.append("\n");

        sb.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            Collections.list(headerNames).forEach(name ->
                    sb.append("  ").append(name).append(": ").append(request.getHeader(name)).append("\n")
            );
        }

        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, StandardCharsets.UTF_8);
            sb.append("Body:\n").append(prettyPrintJson(body)).append("\n");
        }

        sb.append("=============================\n");
        System.out.println(sb.toString());
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n====== Outgoing Response ======\n");
        sb.append("Status: ").append(response.getStatus()).append("\n");

        sb.append("Headers:\n");
        response.getHeaderNames().forEach(name ->
                sb.append("  ").append(name).append(": ").append(response.getHeader(name)).append("\n")
        );

        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, StandardCharsets.UTF_8);
            sb.append("Body:\n").append(prettyPrintJson(body)).append("\n");
        }

        sb.append("Time Taken: ").append(duration).append(" ms\n");
        sb.append("===============================\n");
        System.out.println(sb.toString());
    }

    private String prettyPrintJson(String body) {
        try {
            body = body.trim();
            if (body.startsWith("{")) {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                Object json = mapper.readValue(body, Object.class);
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            }
        } catch (Exception e) {
            // Not JSON or cannot be parsed
        }
        return body;
    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println(request.getHeader("AUTHORIZATION"));
//        filterChain.doFilter(request,response);
//    }
}
