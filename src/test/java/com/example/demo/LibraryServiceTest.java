package com.example.demo;

import com.example.demo.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibraryServiceTest {
    @Autowired
    LibraryService libraryService;

    @Test
    void contextLoadsAndSeeds() {
        var authors = libraryService.listAuthors();
        assertThat(authors).isNotEmpty();
    }
}
