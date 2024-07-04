package com.example.services.serviceImplementations;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;
import com.example.repositories.BookRepository;
import com.example.repositories.FeedBackRepository;
import com.example.repositories.LibrarianRepository;
import com.example.repositories.UserRepository;
import com.example.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void addUser(Users users) {
        userRepo.save(users);
    }

    @Override
    public void updateBook(Long bookId, Book newBook) {
        Optional<Book> savedBook = bookRepo.findById(bookId);
        if(savedBook.isEmpty()){

        }
        Book oldBook = savedBook.get();
        oldBook.setBookName(newBook.getBookName());
        oldBook.setCategory(newBook.getCategory());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setAvailability(newBook.getAvailability());
        oldBook.setUser(newBook.getUser());
        oldBook.setPublication(newBook.getPublication());
        bookRepo.save(oldBook);
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
    public void removeUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<Users> displayAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Feedback> getAllFeedbacksByBookId(Long bookId) {
        return feedbackRepo.findAllByBookName(bookId);
    }

    @Override
    public List<Users> getUsersByName(String name) {
        return userRepo.findUsersByName(name);
    }
}