package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LibraryService {
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    public LibraryService(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public Author createAuthor(String name) {
        return authorRepo.save(new Author(name));
    }

    public Book addBook(Long authorId, String title) {
        Author a = authorRepo.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        Book b = new Book(title);
        a.addBook(b);
        authorRepo.save(a);
        return b;
    }

    public Book getBook(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    public List<Author> listAuthors() { return authorRepo.findAll(); }
}
