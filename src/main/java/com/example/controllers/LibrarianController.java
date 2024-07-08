package com.example.controllers;


import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;
import com.example.services.serviceImplementations.LibrarianServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Book>> displayBooks() {
        List<Book> books = librarianServices.displayBook();
        if (books == null || books.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.ok(books);  // HTTP 200 OK with the list of books
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> displayAllUsers() {
        List<Users> users = librarianServices.displayAllUsers();
        if (users == null || users.isEmpty()) {
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
    public ResponseEntity<List<Feedback>> getAllFeedbacksByBookId(@PathVariable Long bookId) {
        List<Feedback> feedbacks = librarianServices.getAllFeedbacksByBookId(bookId);
        if (feedbacks == null || feedbacks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // HTTP 204 No Content
        }
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);  // HTTP 200 OK with the list of feedbacks
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<Users>> getUsersByName(@PathVariable String name) {
        List<Users> users = librarianServices.getUsersByName(name);
        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // HTTP 204 No Content
        }
        return new ResponseEntity<>(users, HttpStatus.OK);  // HTTP 200 OK with the list of users
    }

}
