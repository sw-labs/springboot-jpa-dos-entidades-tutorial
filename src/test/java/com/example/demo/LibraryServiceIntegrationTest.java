package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LibraryServiceIntegrationTest {
    @Autowired
    LibraryService libraryService;

    @Test
    void addBookHappyPath() {
        Author a = libraryService.createAuthor("Test Author");
        Book b = libraryService.addBook(a.getId(), "Test Book");
        assertThat(b).isNotNull();
        // Reload book via service to verify persistence
        Book persisted = libraryService.getBook(b.getId());
        assertThat(persisted).isNotNull();
        assertThat(persisted.getTitle()).isEqualTo("Test Book");
    }

    @Test
    void addBookAuthorNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            libraryService.addBook(9999L, "No Author Book");
        });
    }
}
