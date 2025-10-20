package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {
    private final LibraryService service;

    public LibraryController(LibraryService service) { this.service = service; }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestParam String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Author a = service.createAuthor(name);
        return ResponseEntity.created(URI.create("/api/authors/" + a.getId())).body(a);
    }

    @PostMapping("/authors/{id}/books")
    public ResponseEntity<Book> addBook(@PathVariable Long id, @RequestParam String title) {
        if (title == null || title.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Book b = service.addBook(id, title);
        return ResponseEntity.created(URI.create("/api/books/" + b.getId())).body(b);
    }

    @GetMapping("/authors")
    public List<Author> listAuthors() { return service.listAuthors(); }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book b = service.getBook(id);
        return ResponseEntity.ok(b);
    }
}
