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
    @Retry(name = "bookService", fallbackMethod = "fallback")
    @RateLimiter(name = "bookService", fallbackMethod = "fallback")
    @TimeLimiter(name = "bookService", fallbackMethod = "fallback")
    public CompletableFuture<String> checkBooks(String type) {
        return CompletableFuture.supplyAsync(() -> {
            switch (type) {
                case "timeout":
                    try { Thread.sleep(4000); } catch (InterruptedException e) {}
                    break;
                case "error":
                    throw new RuntimeException("Simulated error");
                default:
                    break;
            }
            return restTemplate.getForObject("http://localhost:8082/books/user", String.class);
        });
    }


    public CompletableFuture<String> fallback(Throwable t) {
        return CompletableFuture.completedFuture("Fallback: Service is currently unavailable or failed. Error: " + t.getMessage());
    }
}
