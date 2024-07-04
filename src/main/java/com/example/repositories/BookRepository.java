package com.example.repositories;

import com.example.entities.Book;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select b from Book b where b.publication= :publication")
    List<Book> findAllByPublication(@Param("publication") String publication);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.user.id = :userId, b.availability = false WHERE b.bookId = :bookId")
    void updateUserIdByBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    @Modifying
    @Transactional
    @Query("update Book b Set b.user=null, b.availability=true where b.bookId = :id")
    void updateBookDetailById(@Param("id") Long id);

}
