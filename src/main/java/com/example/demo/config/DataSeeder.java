package com.example.demo.config;

import com.example.demo.service.LibraryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    private final LibraryService libraryService;

    public DataSeeder(LibraryService libraryService) { this.libraryService = libraryService; }

    @PostConstruct
    public void seed() {
        // Only seed when no authors exist to avoid duplicates on restart/tests
        if (libraryService.listAuthors().isEmpty()) {
            var a = libraryService.createAuthor("Gabriel García Márquez");
            libraryService.addBook(a.getId(), "Cien años de soledad");
        }
    }
}
