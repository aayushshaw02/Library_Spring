package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;

import java.util.List;


public interface LibrarianService {
    void addBook(Book book);
    void addUser(Users users);
    void updateBook(Long bookId, Book newBook);
    void deleteBookById(Long bookId);
    List<Book> displayBook();

    void removeUser(Long userId);
    List<Users> displayAllUsers();

    List<Feedback> getAllFeedbacksByBookId(Long bookId);

    List<Users> getUsersByName(String name);
}
