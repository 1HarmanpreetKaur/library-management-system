package com.library.library_management;

public class LibraryManagement {

    private DynamicBookArray books;
    private MemberLinkedList members;
    private WaitlistQueue waitingQueue;

    private BorrowRecord[] records;
    private int recordCount;
    private int nextRecordId;

    public LibraryManagement() {
        books = new DynamicBookArray();
        members = new MemberLinkedList();
        waitingQueue = new WaitlistQueue();

        records = new BorrowRecord[5];
        recordCount = 0;
        nextRecordId = 1;
    }

    //Book operations

    public void addBook(int bookId, String title, String author, String category, String ISBN) {
        if (findBookById(bookId) != null) {
            System.out.println("This book ID already exists.");
            return;
        }

        Books newBook = new Books(bookId, title, author, category, ISBN);
        books.addBook(newBook);
        System.out.println("Book added successfully");
    }

    public void deleteBook(int bookId) {
        Books book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book not found,");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is currently borrowed. Can't delete.");
            return;
        }

        books.removeBookById(bookId);
    }

    public void updateBook(int bookId, String title, String author, String category, String isbn) {
        books.updateBook(bookId, title, author, category, isbn);
    }

    public void searchBookByTitle(String title) {
        books.searchByTitle(title);
    }

    public void searchBookByAuthor(String author) {
        books.searchByAuthor(author);
    }

    public void sortBooksByTitle() {
        books.sortByTitle();
        System.out.println("Books sorted by title.");
    }

    public void sortBooksById() {
        books.sortById();
        System.out.println("Books sorted by ID.");
    }

    public void showAllBooks() {
        books.displayAllBooks();
    }

    //Member Operations

    public void registerMember(int memberId, String name, String email, String phone) {
        if (members.searchMember(memberId) != null) {
            System.out.println("Member ID already exists.");
            return;
        }

        Members newMember = new Members(memberId, name, email, phone);
        members.addMember(newMember);
    }

    public void removeMember(int memberId) {
        Members member = members.searchMember(memberId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (hasActiveBorrowRecord(memberId)) {
            System.out.println("Cannot remove member. Member still has borrowed book(s)");
            return;
        }

        members.deleteMember(memberId);
    }

    public void updateMember(int memberId, String name, String email, String phone) {
        members.updateMember(memberId, name, email, phone);
    }

    public void searchMember(int memberId) {
        Members member = members.searchMember(memberId);

        if (member == null) {
            System.out.println("Member not found.");
        } else {
            member.displayMember();
        }
    }

    public void searchMemberByName(String name) {
        members.searchMemberByName(name);
    }

    public void showAllMembers() {
        members.displayMembers();
    }

    //Borrow/Return        O(members + books)
    /*public void borrowBook(int memberId, int bookId, String borrowDate) {
        Members member = members.searchMember(memberId);
        Books book = findBookById(bookId);

        if (member == null) {
            System.out.println("Borrow failed. Member not found.");
            return;
        }

        if (book == null) {
            System.out.println("Borrow failed. Book not found.");
            return;
        }

        if (book.isAvailable()) {
            book.setAvailable(false);

            addBorrowRecord(new BorrowRecord(nextRecordId, bookId, memberId, borrowDate));
            nextRecordId++;

            System.out.println("Book borrowed successfully.");
        }
        else {
            waitingQueue.enqueue(new Request(memberId, bookId));
            System.out.println("Book is not available. Member is added to the waitlist.");
        }
    }*/

    public String borrowBook(int memberId, int bookId, String borrowDate) {
        Members member = members.searchMember(memberId);
        Books book = findBookById(bookId);

        if (member == null) return "Borrow failed. Member not found.";
        if (book == null) return "Borrow failed. Book not found.";

        if (book.isAvailable()) {
            book.setAvailable(false);
            addBorrowRecord(new BorrowRecord(nextRecordId, bookId, memberId, borrowDate));
            nextRecordId++;
            return "Book borrowed successfully.";
        } else {
            waitingQueue.enqueue(new Request(memberId, bookId));
            return "Book is not available. Member added to the waitlist.";
        }
    }


    //O(books + record)
    /*public void returnBook(int memberId, int bookId, String returnDate) {
        Books book = findBookById(bookId);

        if (book == null) {
            System.out.println("Return failed. Book not found.");
            return;
        }

        BorrowRecord activeRecord = findActiveRecord(memberId, bookId);

        if (activeRecord == null) {
            System.out.println("No active borrow record found for this member and book.");
            return;
        }

        activeRecord.markReturned(returnDate);
        book.setAvailable(true);
        System.out.println("Book returned successfully.");
        System.out.println("Please check if someone from the waiting list needs that book.");
    }*/

    public String returnBook(int memberId, int bookId, String returnDate) {
        Books book = findBookById(bookId);

        if (book == null) return "Return failed. Book not found.";

        BorrowRecord activeRecord = findActiveRecord(memberId, bookId);

        if (activeRecord == null) return "No active borrow record found for this member and book.";

        activeRecord.markReturned(returnDate);
        book.setAvailable(true);
        return "Book returned successfully.";
    }

    //O(n)
    public void showAllBorrowRecords() {
        if (recordCount == 0) {
            System.out.println("No borrow records found.");
            return;
        }

        for (int i = 0; i < recordCount; i++) {
            records[i].displayRecord();
            System.out.println("------------------------");
        }
    }

    public void addToWaitlist(int memberId, int bookId) {
        Members member = members.searchMember(memberId);
        Books book = findBookById(bookId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        Request request = new Request(memberId, bookId);
        waitingQueue.enqueue(request);
    }

    public void showWaitlist() {
        waitingQueue.displayQueue();
    }

    //Helper methods

    private Books findBookById(int bookId) {
        for (int i = 0; i < books.getSize(); i++) {
            Books currentBook = books.getBookByIndex(i);
            if (currentBook != null && currentBook.getBookId() == bookId) {
                return currentBook;
            }
        }
        return null;
    }

    private boolean hasActiveBorrowRecord(int memberId) {
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getMemberId() == memberId &&
                    records[i].getStatus().equalsIgnoreCase("Borrowed")) {
                return true;
            }
        }
        return false;
    }

    //O(n)
    private BorrowRecord findActiveRecord(int memberId, int bookId) {
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getMemberId() == memberId &&
                    records[i].getBookId() == bookId &&
                    records[i].getStatus().equalsIgnoreCase("Borrowed")) {
                return records[i];
            }
        }
        return null;
    }

    //O(1) - mostly, O(n) is array full
    private void addBorrowRecord(BorrowRecord record) {
        if (recordCount == records.length) {
            resizeRecordsArray();
        }

        records[recordCount] = record;
        recordCount++;
    }

    private void resizeRecordsArray() {
        BorrowRecord[] newRecords = new BorrowRecord[records.length * 2];

        for (int i = 0; i < recordCount; i++) {
            newRecords[i] = records[i];
        }

        records = newRecords;
    }

    public DynamicBookArray getBooks() {
        return books;
    }

    public MemberLinkedList getMembers() {
        return members;
    }
}
