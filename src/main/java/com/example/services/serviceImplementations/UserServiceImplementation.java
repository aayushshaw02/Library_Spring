package com.example.services.serviceImplementations;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.repositories.BookRepository;
import com.example.repositories.FeedBackRepository;
import com.example.repositories.ReservedBookRepository;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private FeedBackRepository feedbackRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ReservedBookRepository reservedBookRepo;

    @Override
    public Page<Book> displayBooks(PageRequest pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public Page<ReservedBook> displayBorrowedBooksByUserId(Long userId, PageRequest pageable) {
        return reservedBookRepo.findByUserId(userId, pageable);
    }

    @Override
    public Page<ReservedBook> displayDeadlineCrossedBooks(Long userId,LocalDate deadLine, PageRequest pageable) {
        return reservedBookRepo.findAllByUserIdCrossedDeadline(userId,deadLine,pageable);
    }

    @Override
    public void borrowBook(Long userId, Long bookId) {
        bookRepo.updateUserIdByBookId(userId, bookId);
        ReservedBook reservedBook = new ReservedBook();
        reservedBook.setUser(userRepo.findById(userId).get());
        reservedBook.setId(bookId);
        reservedBook.setBorrowedAt(LocalDate.now());
        reservedBook.setDeadline(LocalDate.now().plusDays(2));
        reservedBookRepo.save(reservedBook);
    }

    @Override
    public void returnBook(Long userId, Long bookId) {
        reservedBookRepo.deleteById(bookId);
        bookRepo.updateBookDetailById(bookId);
    }

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public Page<Book> searchBookByPublications(String publication, PageRequest pageable) {
        return bookRepo.findAllByPublication(publication, pageable);
    }

}
