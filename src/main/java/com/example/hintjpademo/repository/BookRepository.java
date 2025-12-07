package com.example.hintjpademo.repository;

import com.example.hintjpademo.model.Book;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @QueryHints(@QueryHint(name = "org.hibernate.comment", value = "hint: BookRepository.findByTitle"))
    Optional<Book> findByTitle(String title);

    @Query(
            value = "/*+ HINT: repository-native */ select * from books b where lower(b.author) = lower(:author)",
            nativeQuery = true
    )
    List<Book> findByAuthorNativeWithHint(@Param("author") String author);
}
