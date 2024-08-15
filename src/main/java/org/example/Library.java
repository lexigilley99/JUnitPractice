package org.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books;
    private final Map<User, List<Book>> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.loans = new HashMap<>();
        Map<User, Long> lateFees = new HashMap<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book from the library by title
    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    // Find all books published in a specific year
    public List<Book> findBooksByYear(int year) {
        return findBooks(book -> book.getPublicationYear() == year);
    }

    // Find all books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        return findBooks(book -> book.getAuthor().equalsIgnoreCase(author));
    }

    // Find the book with the most pages
    public Optional<Book> findBookWithMostPages() {
        return books.stream().max(Comparator.comparingInt(Book::getPages));
    }

    // Find all books with more than n pages
    public List<Book> findBooksWithMoreThanNPages(int n) {
        return findBooks(book -> book.getPages() > n);
    }

    // Print all book titles in the library, sorted alphabetically
    public List<String> getAllBookTitlesSorted() {
        return transformBooks(Book::getTitle).stream().sorted().collect(Collectors.toList());
    }

    // Update the Book class to include a category attribute
    public List<Book> findBooksByCategory(String category) {
        return findBooks(book -> book.getCategory().equalsIgnoreCase(category));
    }

    // System to keep track of which books are currently on loan
    public void loanBook(Book book, User user) {
        if (book.isOnLoan()) {
            System.out.println("Book is already on loan.");
        } else {
            book.setOnLoan(true);
            loans.computeIfAbsent(user, k -> new ArrayList<>()).add(book);
            user.borrowBook(book);
            // set the loan start date to calculate late fees later
        }
    }

    public void returnBook(Book book, User user) {
        if (loans.containsKey(user) && loans.get(user).remove(book)) {
            book.setOnLoan(false);
            user.returnBook(book);
        }
    }

    // Additional methods to manage late fees

    public List<Book> findBooks(Predicate<Book> criteria) {
        return books.stream().filter(criteria).collect(Collectors.toList());
    }

    public void applyToBooks(Consumer<Book> action) {
        books.forEach(action);
    }

    public <R> List<R> transformBooks(Function<Book, R> transformer) {
        return books.stream().map(transformer).collect(Collectors.toList());
    }

    public Book createBook(Supplier<Book> supplier) {
        Book book = supplier.get();
        books.add(book);
        return book;
    }
}
