package com.library.library_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(LibraryController controller) {
        return args -> {
            LibraryManagement library = controller.getLibrary();

            // Seed Books
            library.addBook(1, "The Alchemist", "Paulo Coelho", "Fiction", "978-0062315007");
            library.addBook(2, "Clean Code", "Robert C. Martin", "Technology", "978-0132350884");
            library.addBook(3, "To Kill a Mockingbird", "Harper Lee", "Classic", "978-0061935466");
            library.addBook(4, "1984", "George Orwell", "Dystopian", "978-0451524935");
            library.addBook(5, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", "978-0743273565");

            // Seed Members
            library.registerMember(101, "Alice Johnson", "alice@email.com", "555-1001");
            library.registerMember(102, "Bob Smith", "bob@email.com", "555-1002");
            library.registerMember(103, "Carol White", "carol@email.com", "555-1003");

            // Seed a borrow record (Book 3 borrowed by Alice)
            library.borrowBook(101, 3, "2026-04-20");
        };
    }
}
