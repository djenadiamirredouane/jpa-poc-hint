package com.example.hintjpademo.repository;

import com.example.hintjpademo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import jakarta.persistence.QueryHint;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @QueryHints(@QueryHint(name = "org.hibernate.comment", value = "hint: BookRepository.findByTitle"))
    Optional<Book> findByTitle(String title);
}
