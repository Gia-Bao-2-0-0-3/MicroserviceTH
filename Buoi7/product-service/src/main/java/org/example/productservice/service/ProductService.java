package org.example.productservice.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ProductService {
    @GetMapping("/user")
    public String getBooks() {
        return "Books for the user";
    }
}
