package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Effective Java", "Joshua Bloch", 2008, 416, "Programming");
        book2 = new Book("Clean Code", "Robert C. Martin", 2008, 464, "Programming");
        Book book3 = new Book("Java Concurrency in Practice", "Brian Goetz", 2006, 384, "Programming");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
    }

    @Test
    void testAddBook() {
        Book newBook = new Book("Design Patterns", "Erich Gamma", 1994, 395, "Software Engineering");
        library.addBook(newBook);

        List<Book> books = library.findBooks(book -> book.getTitle().equals("Design Patterns"));
        assertEquals(1, books.size());
        assertEquals("Design Patterns", books.get(0).getTitle());
    }

    @Test
    void testRemoveBookByTitle() {
        library.removeBookByTitle("Effective Java");

        List<Book> books = library.findBooks(book -> book.getTitle().equals("Effective Java"));
        assertTrue(books.isEmpty());
    }

    @Test
    void testFindBooksByYear() {
        List<Book> books2008 = library.findBooksByYear(2008);

        assertEquals(2, books2008.size());
        assertTrue(books2008.contains(book1));
        assertTrue(books2008.contains(book2));
    }

    @Test
    void testFindBookWithMostPages() {
        Optional<Book> bookWithMostPages = library.findBookWithMostPages();

        assertTrue(bookWithMostPages.isPresent());
        assertEquals(book2, bookWithMostPages.get());
    }

    @Test
    void testGetAllBookTitlesSorted() {
        List<String> sortedTitles = library.getAllBookTitlesSorted();

        List<String> expected = Arrays.asList("Clean Code", "Effective Java", "Java Concurrency in Practice");
        assertEquals(expected, sortedTitles);
    }
}

