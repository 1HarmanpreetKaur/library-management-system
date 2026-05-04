package com.library.library_management;

public class Books {

    private int bookId;    //unique identifier for book
    private String title;      //Title of the book
    private String author;      //Author's full name
    private String category;      //Genre of book
    private String ISBN;      //International Standard Book Number
    private boolean isAvailable;        //to check availability of book

    public Books(int bookID, String title, String author, String category, String ISBN) {
        this.bookId = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
        this.isAvailable = true;        //every new book is initially available
    }

    //Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    //Setters

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    //Print details of the book
    public void displayBook() {
        System.out.println("\nBook ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Category  : " + category);
        System.out.println("ISBN      : " + ISBN);
        System.out.println("Available : " + (isAvailable ? "Yes" : "No"));
    }

    //Update information of book
    public void updateBookInfo(String title, String author, String category, String ISBN) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }
}
