package com.example.repositories;

import com.example.entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {

    @Query("Select f from Feedback f where f.bookId = :bookId")
    Page<Feedback> findByBookId(@Param("bookId")Long bookId,PageRequest pageRequest);

}