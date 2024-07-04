package com.example.controllers;


import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;
import com.example.services.serviceImplementations.LibrarianServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @Autowired
    LibrarianServiceImplementation librarianServices;

    @GetMapping("/books")
    public List<Book> displayBooks(){
        return librarianServices.displayBook();
    }

    @GetMapping("/users")
    public List<Users> displayAllUsers(){
        return librarianServices.displayAllUsers();
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book){
        librarianServices.addBook(book);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody Users users){librarianServices.addUser(users);}

    @PutMapping("/books/{book_id}")
    public void updateBook(@PathVariable Long book_id,@RequestBody Book newBook){
        librarianServices.updateBook(book_id ,newBook);
    }

    @DeleteMapping("/users/{userId}")
    public void removeUser(Long userId){
        librarianServices.removeUser(userId);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBookById(Long bookId){
        librarianServices.deleteBookById(bookId);
    }

    @GetMapping("/feedbacks/book/{bookId}")
    public List<Feedback> getAllFeedbacksByBookId(Long bookId){
        return librarianServices.getAllFeedbacksByBookId(bookId);
    }

    @GetMapping("/users/{name}")
    public List<Users> getUsersByName(@PathVariable String name){
        return librarianServices.getUsersByName(name);
    }

}
