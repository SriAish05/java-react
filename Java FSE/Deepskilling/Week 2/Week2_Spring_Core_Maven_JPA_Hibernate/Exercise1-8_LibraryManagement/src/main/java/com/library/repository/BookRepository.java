package com.library.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 1: BookRepository - Data access layer for managing books.
 * Exercise 6: Annotated with @Repository for component scanning.
 */
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private List<String> books = new ArrayList<>();

    public BookRepository() {
        // Initialize with some sample books
        books.add("Spring in Action");
        books.add("Clean Code");
        books.add("Effective Java");
    }

    public List<String> findAllBooks() {
        return books;
    }

    public void addBook(String book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public String findBookByTitle(String title) {
        return books.stream()
                .filter(b -> b.equalsIgnoreCase(title))
                .findFirst()
                .orElse("Book not found");
    }

    public void removeBook(String book) {
        books.remove(book);
        System.out.println("Book removed: " + book);
    }
}
