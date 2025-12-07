package com.example.hintjpademo.service;

import com.example.hintjpademo.model.Book;
import com.example.hintjpademo.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Book> findByTitleWithRepositoryHint(String title) {
        return bookRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthorWithEntityManagerHint(String author) {
        var query = entityManager.createQuery(
                "select b from Book b where lower(b.author) = lower(:author)", Book.class);
        query.setParameter("author", author);
        query.setHint("org.hibernate.readOnly", true);
        query.setHint("org.hibernate.comment", "hint: EntityManager.findByAuthorWithEntityManagerHint");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthorWithRepositoryNativeHint(String author) {
        return bookRepository.findByAuthorNativeWithHint(author);
    }
}
