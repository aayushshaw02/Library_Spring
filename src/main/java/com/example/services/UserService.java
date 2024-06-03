package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.ReservedBook;
import com.example.entities.User;

import java.util.List;

public interface UserService {

    void displayBooks();

    void displayBorrowedBooks(String userId);

    List<ReservedBook> displayDeadlineCrossedBook(Long userId);

    void BorrowBook(Long userId, Long bookId);

    void RenewBook(User user, ReservedBook book);

    void returnBook(User user, ReservedBook book);

    void submitFeedback(Feedback feedback);

    List<Book> searchBookByPublications(String publication);

}
