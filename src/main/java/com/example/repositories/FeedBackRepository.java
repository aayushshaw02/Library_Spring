package com.example.repositories;

import com.example.entities.Feedback;
import com.example.entities.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {

    @Query("Select f from Feedback f where f.bookId = :bookId")
    void findAllByBookName(@Param("bookId")Long bookId);
}