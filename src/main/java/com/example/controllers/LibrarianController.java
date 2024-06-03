package com.example.controllers;


import com.example.entities.Book;
import com.example.entities.User;
import com.example.services.serviceImplementations.LibrarianServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LibrarianController {

    @Autowired
    LibrarianServiceImplementation librarianServices;

    public List<Book> displayBooks(){
        return librarianServices.displayBook();
    }

    public List<User> displayAllUsers(){
        return librarianServices.displayAllUsers();
    }

    public void addBook(Book book){
        librarianServices.addBook(book);
    }

    public void addUser(User user){librarianServices.addUser(user);}

    public void updateBook(Book oldBook, Book newBook){
        librarianServices.updateBook(oldBook, newBook);
    }

    public void getAllFeedback(){
        librarianServices.getAllFeedback();
    }

    public void removeUser(Long userId){
        librarianServices.removeUser(userId);
    }

    public void deleteBookById(Long bookId){
        librarianServices.deleteBookById(bookId);
    }

    public void getAllFeedbacksByBookId(Long bookId){
        librarianServices.getAllFeedbacksByBookName(bookId);
    }

    public List<User> getUsersByName(String name){
        return librarianServices.getUsersByName(name);
    }

}
