package com.example.controllers;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.entities.User;
import com.example.services.serviceImplementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImplementation userServicesImp;


    public void displayBook(){
        userServicesImp.displayBooks();
    }

    public void displayBorrowedBook(String userId){
        userServicesImp.displayBorrowedBooks(userId);
    }

    public List<ReservedBook> displayDeadlineCrossedBook(Long userId){
        return userServicesImp.displayDeadlineCrossedBook(userId);
    }

    public void borrowBook(Long userId, Long bookId){
        userServicesImp.BorrowBook(userId, bookId);
    }

    public void RenewBook(User user, ReservedBook book){
        userServicesImp.RenewBook(user, book);
    }

    public void returnBook(User user, ReservedBook book) {
        userServicesImp.returnBook(user, book);
    }

    public void submitFeedback(Feedback feedback) {
        userServicesImp.submitFeedback(feedback);
    }

    public List<Book> searchBookByPublications(String publication){
        return userServicesImp.searchBookByPublications(publication);
    }

}
