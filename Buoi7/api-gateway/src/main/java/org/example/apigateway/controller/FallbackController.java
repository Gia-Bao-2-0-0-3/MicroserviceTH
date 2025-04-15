package org.example.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/auth")
    public String fallbackAuth() {
        return "Fallback from API Gateway - Auth Service";
    }

    @GetMapping("/fallback/book")
    public String fallbackBook() {
        return "Fallback from API Gateway - Book Service";
    }
}
