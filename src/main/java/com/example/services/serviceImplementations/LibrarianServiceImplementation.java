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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Users addUser(Users users) {
        return userRepo.save(users);
    }

    @Override
    public boolean updateBook(Long bookId, Book newBook) {
        Optional<Book> savedBook = bookRepo.findById(bookId);
        if(savedBook.isEmpty()){
            return false;
        }
        Book oldBook = savedBook.get();
        oldBook.setBookName(newBook.getBookName());
        oldBook.setCategory(newBook.getCategory());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setAvailability(newBook.getAvailability());
        oldBook.setUser(newBook.getUser());
        oldBook.setPublication(newBook.getPublication());
        bookRepo.save(oldBook);
        return true;
    }

    @Override
    public boolean deleteBookById(Long bookId) {
        if(bookRepo.findById(bookId).isEmpty()) {
            return false;
        }
        bookRepo.deleteById(bookId);
        return true;
    }

    public Page<Book> displayBook(PageRequest pageRequest) {
        return bookRepo.findAll(pageRequest);
    }

    @Override
    public boolean removeUser(Long userId) {
        if( userRepo.findById(userId).isEmpty()){
            return false;
        }
        userRepo.deleteById(userId);
        return true;
    }

    @Override
    public Page<Users> displayAllUsers(PageRequest pageRequest) {
        return userRepo.findAll(pageRequest);
    }

    @Override
    public Page<Feedback> getAllFeedbacksByBookId(Long bookId, PageRequest pageRequest) {
        return feedbackRepo.findByBookId(bookId, pageRequest);
    }

    @Override
    public Page<Users> getUsersByName(String name, PageRequest pageRequest) {
        return userRepo.findUsersByName(name, pageRequest);
    }
}