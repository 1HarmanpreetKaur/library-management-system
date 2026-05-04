package com.library.library_management;

public class DynamicBookArray {

    private Books[] books;     //Array to store books
    private int size;        //No. of books currently stored

    public DynamicBookArray() {
        books = new Books[5];       //Create an array with initial capacity of 5
        size = 0;        //Because no book is stored yet
    }

    //Method to get current number of books
    public int getSize() {
        return size;
    }

    //Method to increase array size when full
    public void resizeArray() {             //O(n)
        Books[] newBooks = new Books[books.length * 2];

        //Copy each book from previous array to new array
        for (int i = 0; i < size; i++) {
            newBooks[i] = books [i];
        }

        books = newBooks;    //Replace old array with new
    }

    //Add a book
    public void addBook(Books b) {   //O(1) normally, O(n) if array is full
        if (size == books.length) {
            resizeArray();       //Resize the array if full
        }

        //Store the book at the next available index and change the number of books stored
        books[size] = b;
        size++;
    }

    //remove book using its ID
    public void removeBookById(int id) {    //O(n)
        int index = -1;    //initialize index as not found

        for (int i = 0; i < size; i++) {
            if (books[i].getBookId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Book not found.");
            return;
        }

        //Move the books to left, in the empty space
        for (int i = index; i < size - 1; i++) {
            books[i] = books[i+1];
        }

        books[size - 1] = null;      //Remove duplicate last entry
        size--;     //Change total number of books

        System.out.println("Book removed successfully.");
    }

    //Update book     O(n)
    public void updateBook(int id, String title, String author, String category, String ISBN) {

        for (int i = 0; i < size; i++) {
            if (books[i].getBookId() == id) {
                books[i].updateBookInfo(title, author, category, ISBN);
                System.out.println("Book updated successfully");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    //Search book by title    O(n)
    public void searchByTitle(String title) {
        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                books[i].displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No book found with that title");
        }
    }

    //Search book by author        O(n)
    public void searchByAuthor(String author) {
        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                books[i].displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No book found with that author");
        }
    }

    //Sort books by Title     O(n^2)
    public void sortByTitle() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    Books temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }


    }

    //Sort books by ID        O(n^2)
    public void sortById() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (books[j].getBookId() > books[j + 1].getBookId()) {
                    Books temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }


    }

    //Get book by index        O(1)
    public Books getBookByIndex(int index) {
        if (index >= 0 && index < size) {
            return books[index];
        }
        return null;
    }

    //To display all books
    public void displayAllBooks() {
        if (size == 0) {
            System.out.println("No books available.");
            return;
        }

        for (int i = 0; i < size; i++) {
            books[i].displayBook();
        }
    }

}
