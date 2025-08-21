package com.walkway.edge_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public Mono<ResponseEntity<String>> home(@RequestParam(required = false) String message) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .body("")
        );
    }

    @GetMapping("/error")
    public Mono<ResponseEntity<String>> handleError(@RequestParam(required = false) String message) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("An error occurred ")
        );
    }
}

