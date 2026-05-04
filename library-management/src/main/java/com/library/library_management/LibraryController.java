package com.library.library_management;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LibraryController {

    private final LibraryManagement library = new LibraryManagement();

    // ---- BOOKS ----
    @PostMapping("/books/add")
    public String addBook(@RequestParam int bookId,
                          @RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String category,
                          @RequestParam String isbn) {
        library.addBook(bookId, title, author, category, isbn);
        return "Book added!";
    }

    @DeleteMapping("/books/delete")
    public String deleteBook(@RequestParam int bookId) {
        library.deleteBook(bookId);
        return "Book deleted!";
    }

    @PutMapping("/books/update")
    public String updateBook(@RequestParam int bookId,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String category,
                             @RequestParam String isbn) {
        library.updateBook(bookId, title, author, category, isbn);
        return "Book updated!";
    }

    @GetMapping("/books/all")
    public List<Books> getAllBooks() {
        List<Books> result = new ArrayList<>();
        DynamicBookArray bookArray = library.getBooks();
        for (int i = 0; i < bookArray.getSize(); i++) {
            result.add(bookArray.getBookByIndex(i));
        }
        return result;
    }

    @GetMapping("/books/search/title")
    public List<Books> searchByTitle(@RequestParam String title) {
        List<Books> result = new ArrayList<>();
        DynamicBookArray bookArray = library.getBooks();
        for (int i = 0; i < bookArray.getSize(); i++) {
            Books b = bookArray.getBookByIndex(i);
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    @GetMapping("/books/search/author")
    public List<Books> searchByAuthor(@RequestParam String author) {
        List<Books> result = new ArrayList<>();
        DynamicBookArray bookArray = library.getBooks();
        for (int i = 0; i < bookArray.getSize(); i++) {
            Books b = bookArray.getBookByIndex(i);
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // ---- MEMBERS ----
    @PostMapping("/members/add")
    public String addMember(@RequestParam int memberId,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String phone) {
        library.registerMember(memberId, name, email, phone);
        return "Member registered!";
    }

    @DeleteMapping("/members/delete")
    public String removeMember(@RequestParam int memberId) {
        library.removeMember(memberId);
        return "Member removed!";
    }

    @GetMapping("/members/all")
    public List<Members> getAllMembers() {
        return library.getMembers().getAllMembers();
    }

    // ---- BORROW / RETURN ----
  /*  @PostMapping("/borrow")
    public String borrowBook(@RequestParam int memberId,
                             @RequestParam int bookId,
                             @RequestParam String borrowDate) {
        library.borrowBook(memberId, bookId, borrowDate);
        return "Borrow processed!";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int memberId,
                             @RequestParam int bookId,
                             @RequestParam String returnDate) {
        library.returnBook(memberId, bookId, returnDate);
        return "Return processed!";
    }*/

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam int memberId,
                             @RequestParam int bookId,
                             @RequestParam String borrowDate) {
        return library.borrowBook(memberId, bookId, borrowDate);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int memberId,
                             @RequestParam int bookId,
                             @RequestParam String returnDate) {
        return library.returnBook(memberId, bookId, returnDate);
    }

    // ---- WAITLIST ----
    @GetMapping("/waitlist")
    public String getWaitlist() {
        library.showWaitlist();
        return "Check console for waitlist.";
    }

    public LibraryManagement getLibrary() {
        return library;
    }
}