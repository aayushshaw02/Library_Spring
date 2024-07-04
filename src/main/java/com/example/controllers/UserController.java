package com.example.controllers;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.entities.Users;
import com.example.services.serviceImplementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImplementation userServicesImp;

    @GetMapping("/books")
    public void displayBook(){
        userServicesImp.displayBooks();
    }

    @GetMapping("/{userId}/borrowed-books")
    public void displayBorrowedBook(@PathVariable Long userId){
        userServicesImp.displayBorrowedBooksByUserId(userId);
    }

    @GetMapping("/{userId}/deadline-crossed-books")
    public List<ReservedBook> displayDeadlineCrossedBook(@PathVariable Long userId){
        return userServicesImp.displayDeadlineCrossedBook(userId);
    }

    @PostMapping("/{userId}/borrow/{bookId}")
    public void borrowBook(@RequestBody Long userId,@RequestBody Long bookId){
        userServicesImp.BorrowBook(userId, bookId);
    }

    @PostMapping("/return/{userId}")
    public void returnBook(@PathVariable Long userId,@RequestBody ReservedBook book) {
        userServicesImp.returnBook(userId, book);
    }

    @PostMapping("/feedback")
    public void submitFeedback(@RequestBody Feedback feedback) {
        userServicesImp.submitFeedback(feedback);
    }

    @GetMapping("/{publication}")
    public List<Book> searchBookByPublications(@PathVariable String publication){
        return userServicesImp.searchBookByPublications(publication);
    }

}
