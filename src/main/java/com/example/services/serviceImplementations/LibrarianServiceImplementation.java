package com.example.services.serviceImplementations;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.User;
import com.example.repositories.BookRepository;
import com.example.repositories.FeedBackRepository;
import com.example.repositories.LibrarianRepository;
import com.example.repositories.UserRepository;
import com.example.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianServiceImplementation implements LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private FeedBackRepository feedbackRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void addBook(Book book) {
        bookRepo.save(book);
    }

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateBook(Book oldBook, Book newBook) {

    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepo.deleteById(bookId);
    }

    @Override
    public List<Book> displayBook() {
        return bookRepo.findAll();
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }



    @Override
    public void removeUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<User> displayAllUsers() {
        return userRepo.findAll();
    }



    @Override
    public void getAllFeedbacksByBookName(Long bookId) {
        feedbackRepo.findAllByBookName(bookId);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepo.findUsersByName(name);
    }
}