package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * LibraryManagementApplication - Main class to test Spring Core features.
 *
 * Exercise 1: Load Spring context and test basic configuration.
 * Exercise 2: Verify Dependency Injection (BookRepository into BookService).
 * Exercise 3: Observe AOP logging for method execution times.
 * Exercise 5: Test IoC Container configuration.
 * Exercise 6: Verify annotation-based configuration with @Service, @Repository.
 * Exercise 7: Test both constructor and setter injection.
 * Exercise 8: Observe AOP Before/After advice in console logs.
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        System.out.println("=== Library Management Application ===\n");

        // Exercise 1 & 5: Load the Spring Application Context
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Spring Application Context loaded successfully!\n");

        // Exercise 6: Get bean via component scanning (annotation-based)
        BookService bookService = context.getBean(BookService.class);

        // Exercise 2: Test Dependency Injection - BookRepository is injected
        System.out.println("\n--- Testing Dependency Injection ---");
        System.out.println("BookRepository injected: " + (bookService.getBookRepository() != null));

        // Exercise 3 & 8: These method calls will trigger AOP logging
        System.out.println("\n--- Testing BookService Methods (AOP will log) ---");

        // Get all books
        List<String> books = bookService.getAllBooks();
        System.out.println("All Books: " + books);

        // Add a new book
        bookService.addBook("Head First Design Patterns");

        // Search for a book
        String found = bookService.searchBook("Clean Code");
        System.out.println("Search Result: " + found);

        // Get all books again to see the new addition
        books = bookService.getAllBooks();
        System.out.println("Updated Books: " + books);

        // Remove a book
        bookService.removeBook("Clean Code");
        books = bookService.getAllBooks();
        System.out.println("After Removal: " + books);

        System.out.println("\n=== Application Completed Successfully ===");

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
    }
}
