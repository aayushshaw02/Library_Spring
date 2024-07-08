package com.example.controllers;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.entities.Users;
import com.example.services.serviceImplementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImplementation userServicesImp;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> displayBook() {
        List<Book> books = userServicesImp.displayBooks();
        if (books == null || books.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(books);  // HTTP 200 OK with the list of books
    }

    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<List<ReservedBook>> displayBorrowedBook(@PathVariable Long userId) {
        List<ReservedBook> borrowedBooks = userServicesImp.displayBorrowedBooksByUserId(userId);
        if (borrowedBooks == null || borrowedBooks.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(borrowedBooks);  // HTTP 200 OK with the list of borrowed books
    }

    @GetMapping("/{userId}/deadline-crossed-books")
    public ResponseEntity<List<ReservedBook>> displayDeadlineCrossedBook(@PathVariable Long userId) {
        List<ReservedBook> deadlineCrossedBooks = userServicesImp.displayDeadlineCrossedBook(userId);
        if (deadlineCrossedBooks == null || deadlineCrossedBooks.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(deadlineCrossedBooks);  // HTTP 200 OK with the list of deadline-crossed books
    }

    @PostMapping("/{userId}/borrowed-books")
    public ResponseEntity<Void> borrowBook(@PathVariable Long userId, @RequestParam Long bookId) {
        try {
            userServicesImp.borrowBook(userId, bookId);

            // Build URI for the created resource
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{bookId}")
                    .buildAndExpand(bookId)
                    .toUri();

            // Return ResponseEntity with created status and location header
            return ResponseEntity.created(uri).build();  // HTTP 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // HTTP 500 Internal Server Error
        }
    }

    @DeleteMapping("/{userId}/borrowed-books")
    public ResponseEntity<Void> returnBook(@PathVariable Long userId, @RequestParam Long bookId) {
        try {
            userServicesImp.returnBook(userId, bookId);
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // HTTP 500 Internal Server Error
        }
    }

    @PostMapping("/feedback")
    public ResponseEntity<Void> submitFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback createdFeedback = userServicesImp.submitFeedback(feedback);

            // Build URI for the created resource
            String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdFeedback.getId())
                    .toUriString();

            // Return ResponseEntity with created status and location header
            return ResponseEntity.created(new URI(uri)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{publication}")
    public ResponseEntity<List<Book>> searchBookByPublications(@PathVariable String publication) {
        List<Book> books = userServicesImp.searchBookByPublications(publication);
        if (books == null || books.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(books);  // HTTP 200 OK with the list of books
    }

}
