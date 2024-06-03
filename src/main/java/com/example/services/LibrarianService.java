package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Librarian;
import com.example.entities.User;
import com.example.repositories.LibrarianRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LibrarianService {
    void addBook(Book book);
    void addUser(User user);
    void updateBook(Book oldBook, Book newBook);
    void deleteBookById(Long bookId);
    List<Book> displayBook();
    List<Feedback> getAllFeedback();

    void removeUser(Long userId);
    List<User> displayAllUsers();

    void getAllFeedbacksByBookName(Long bookId);

    List<User> getUsersByName(String name);
}
