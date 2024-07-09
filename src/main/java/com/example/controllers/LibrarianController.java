package com.example.controllers;


import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;
import com.example.services.serviceImplementations.LibrarianServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @Autowired
    LibrarianServiceImplementation librarianServices;

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> displayBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> books = librarianServices.displayBook(PageRequest.of(page, size));
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(books);  // HTTP 200 OK with the list of books
    }


    @GetMapping("/users")
    public ResponseEntity<Page<Users>> displayAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Users> users = librarianServices.displayAllUsers(PageRequest.of(page, size));
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(users);  // HTTP 200 OK with the list of users
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        try {
            Book createdBook = librarianServices.addBook(book);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdBook.getBookId())
                    .toUri();
            return ResponseEntity.created(location).build();  // HTTP 201 Created with location header
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // HTTP 500 Internal Server Error
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@RequestBody Users users) {
        try {
            Users createdUser = librarianServices.addUser(users);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdUser.getId())
                    .toUri();
            return ResponseEntity.created(location).build();  // HTTP 201 Created with location header
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // HTTP 500 Internal Server Error
        }
    }

    @PutMapping("/books/{book_id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long book_id, @RequestBody Book newBook) {
        try {
            boolean isUpdated = librarianServices.updateBook(book_id, newBook);
            if (isUpdated) {
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .buildAndExpand(book_id)
                        .toUri();
                return ResponseEntity.ok().location(location).build();  // HTTP 200 OK with location header
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // HTTP 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // HTTP 500 Internal Server Error
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Long userId) {
        try {
            boolean isDeleted = librarianServices.removeUser(userId);
            if (isDeleted) {
                return ResponseEntity.noContent().build();  // HTTP 204 No Content
            } else {
                return ResponseEntity.notFound().build();  // HTTP 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // HTTP 500 Internal Server Error
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long bookId) {
        try {
            boolean isDeleted = librarianServices.deleteBookById(bookId);
            if (isDeleted) {
                return ResponseEntity.noContent().build();  // HTTP 204 No Content
            } else {
                return ResponseEntity.notFound().build();  // HTTP 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // HTTP 500 Internal Server Error
        }
    }

    @GetMapping("/feedbacks/book/{bookId}")
    public ResponseEntity<Page<Feedback>> getAllFeedbacksByBookId(
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Feedback> feedbacks = librarianServices.getAllFeedbacksByBookId(bookId, PageRequest.of(page, size));
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(feedbacks);  // HTTP 200 OK with the list of feedbacks
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<Page<Users>> getUsersByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Users> users = librarianServices.getUsersByName(name, PageRequest.of(page, size));
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(users);  // HTTP 200 OK with the list of users
    }

}
