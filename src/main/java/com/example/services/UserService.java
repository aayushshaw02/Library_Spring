package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;

import java.util.List;

public interface UserService {

    List<Book> displayBooks();

    List<ReservedBook> displayBorrowedBooksByUserId(Long userId);

    List<ReservedBook> displayDeadlineCrossedBook(Long userId);

    void borrowBook(Long userId, Long bookId);

    void returnBook(Long userId, Long bookId);

    Feedback submitFeedback(Feedback feedback);

    List<Book> searchBookByPublications(String publication);

}
