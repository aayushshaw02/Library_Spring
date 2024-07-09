package com.example.services;

import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface LibrarianService {
    Book addBook(Book book);
    Users addUser(Users users);
    boolean updateBook(Long bookId, Book newBook);
    boolean deleteBookById(Long bookId);
    Page<Book> displayBook(PageRequest pageRequest);

    boolean removeUser(Long userId);
    Page<Users> displayAllUsers(PageRequest pageRequest);

    Page<Feedback> getAllFeedbacksByBookId(Long bookId,PageRequest pageRequest);

    Page<Users> getUsersByName(String name,PageRequest pageRequest);
}
