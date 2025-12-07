package com.example.hintjpademo.config;

import com.example.hintjpademo.model.Book;
import com.example.hintjpademo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedBooks(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book("Clean Architecture", "Robert C. Martin"));
                bookRepository.save(new Book("Domain-Driven Design", "Eric Evans"));
                bookRepository.save(new Book("Effective Java", "Joshua Bloch"));
            }
        };
    }
}
