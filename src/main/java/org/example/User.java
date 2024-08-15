package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String libraryCardNumber;
    private final List<Book> borrowedBooks;

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public String getName() { return name; }
    public String getLibraryCardNumber() { return libraryCardNumber; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }
}
