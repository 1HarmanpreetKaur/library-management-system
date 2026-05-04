package com.library.library_management;

public class BorrowRecord {

    private int recordId;
    private int bookId;
    private int memberId;
    private String borrowDate;
    private String returnDate;
    private String status;

    public BorrowRecord(int recordId, int bookId, int memberId, String borrowDate) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = "Not returned yet";
        this.status = "Borrowed";
    }

    //Getters

    public int getRecordId() {
        return recordId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    //Setters

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Mark the book as returned
    public void markReturned(String returnDate) {
        this.returnDate = returnDate;
        this.status = "Returned";
    }

    //Display all information about borrow records
    public void displayRecord() {
        System.out.println("Record ID   : " + recordId);
        System.out.println("Book ID     : " + bookId);
        System.out.println("Member ID   : " + memberId);
        System.out.println("Borrow Date : " + borrowDate);
        System.out.println("Return Date : " + returnDate);
        System.out.println("Status      : " + status);
    }
}
