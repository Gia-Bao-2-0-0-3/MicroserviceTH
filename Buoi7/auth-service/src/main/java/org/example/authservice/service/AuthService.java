package org.example.authservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service

public class AuthService {
    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "bookService", fallbackMethod = "fallback")
    @Retry(name = "bookService")
    @TimeLimiter(name = "bookService")
    public CompletableFuture<String> checkBooks() {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8082/books/user", String.class)
        );
    }

    public CompletableFuture<String> fallback(Throwable t) {
        return CompletableFuture.completedFuture("Fallback from AuthService");
    }

    @RateLimiter(name = "bookService", fallbackMethod = "rateLimiterFallback")
    public CompletableFuture<String> fetchBooks() {
        System.out.println("Rate Limited ....");
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8082/books/user", String.class)
        );
    }

    // Phương thức fallback khi giới hạn tốc độ bị vượt quá
    public CompletableFuture<String> rateLimiterFallback(Throwable t) {
        return CompletableFuture.completedFuture("Rate limit exceeded. Please try again later.");
    }
    @Retry(name = "bookService", fallbackMethod = "retryFallback")
    public CompletableFuture<String> retry() {
        System.out.println("Retrying...");
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a call to an external service
            throw new RuntimeException("Service unavailable");
        });
    }

    public CompletableFuture<String> retryFallback(Throwable t) {
        return CompletableFuture.completedFuture("Fallback from Retry");
    }
}
