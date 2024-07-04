package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.entities.Users;

import java.util.List;

public interface UserService {

    List<Book> displayBooks();

    List<ReservedBook> displayBorrowedBooksByUserId(Long userId);

    List<ReservedBook> displayDeadlineCrossedBook(Long userId);

    void BorrowBook(Long userId, Long bookId);

    void returnBook(Long userId, ReservedBook book);

    void submitFeedback(Feedback feedback);

    List<Book> searchBookByPublications(String publication);

}
