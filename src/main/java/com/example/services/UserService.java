package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    Page<Book> displayBooks(PageRequest pageRequest);

    Page<ReservedBook> displayBorrowedBooksByUserId(Long userId, PageRequest pageable);
    Page<ReservedBook> displayDeadlineCrossedBooks(Long userId, LocalDate Deadline, PageRequest pageable);
    void borrowBook(Long userId, Long bookId);
    void returnBook(Long userId, Long bookId);
    Feedback submitFeedback(Feedback feedback);
    Page<Book> searchBookByPublications(String publication, PageRequest pageable);

}
