package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Exercise 1: BookService - Business logic layer.
 * Exercise 2: Dependency Injection via setter method.
 * Exercise 6: Annotated with @Service for component scanning.
 * Exercise 7: Both Constructor and Setter injection demonstrated.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    // Exercise 7: Constructor Injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService created via Constructor Injection");
    }

    // Default constructor (needed for setter-only injection via XML)
    public BookService() {
        System.out.println("BookService created via Default Constructor");
    }

    // Exercise 2 & 7: Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected via Setter Injection");
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public List<String> getAllBooks() {
        System.out.println("BookService: Fetching all books...");
        return bookRepository.findAllBooks();
    }

    public void addBook(String book) {
        System.out.println("BookService: Adding a new book...");
        bookRepository.addBook(book);
    }

    public String searchBook(String title) {
        System.out.println("BookService: Searching for book - " + title);
        return bookRepository.findBookByTitle(title);
    }

    public void removeBook(String book) {
        System.out.println("BookService: Removing book...");
        bookRepository.removeBook(book);
    }
}
