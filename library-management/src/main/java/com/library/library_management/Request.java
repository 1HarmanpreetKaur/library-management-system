package com.library.library_management;

public class Request {

    private int memberId;
    private int bookId;

    public Request(int memberId, int bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void displayRequest() {
        System.out.println("Member Id : " + memberId);
        System.out.println("Book ID   : " + bookId);
    }
}
