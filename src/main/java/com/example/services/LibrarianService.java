package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;

import java.util.List;


public interface LibrarianService {
    Book addBook(Book book);
    Users addUser(Users users);
    boolean updateBook(Long bookId, Book newBook);
    boolean deleteBookById(Long bookId);
    List<Book> displayBook();

    boolean removeUser(Long userId);
    List<Users> displayAllUsers();

    List<Feedback> getAllFeedbacksByBookId(Long bookId);

    List<Users> getUsersByName(String name);
}
