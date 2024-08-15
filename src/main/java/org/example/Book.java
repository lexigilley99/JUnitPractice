package org.example;

public class Book {
    private final String title;
    private final String author;
    private final int publicationYear;
    private final int pages;
    private final String category;
    private boolean isOnLoan;

    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.category = category;
        this.isOnLoan = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getPages() { return pages; }
    public String getCategory() { return category; }
    public boolean isOnLoan() { return isOnLoan; }
    public void setOnLoan(boolean onLoan) { isOnLoan = onLoan; }
}
