package org.example.authservice.controller;

import org.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/books")
    public CompletableFuture<String> getBooks() {
        return authService.fetchBooks();
    }
    @GetMapping("/books/user")
    public CompletableFuture<String> getBooksUser() {
        return authService.checkBooks();
    }

    @GetMapping("/retry")
    public CompletableFuture<String> retry() {
        return authService.retry();
    }
}
