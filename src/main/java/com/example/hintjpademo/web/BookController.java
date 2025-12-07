package com.example.hintjpademo.web;

import com.example.hintjpademo.model.Book;
import com.example.hintjpademo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitleWithRepositoryHint(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author) {
        return bookService.findByAuthorWithEntityManagerHint(author);
    }
}
