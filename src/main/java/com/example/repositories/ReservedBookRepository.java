package com.example.repositories;

import com.example.entities.ReservedBook;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedBookRepository extends JpaRepository<ReservedBook, Long> {

    @Query("Select b from ReservedBook b where b.user.id = :userId and b.deadline = :deadline")
    List<ReservedBook> findAllByUserIdCrossedDeadline(@Param("userId") Long userId, @Param("deadline") String deadline);

    @Query("Select b from ReservedBook b where b.user.id = :userId")
    List<ReservedBook> findByUserId(@Param("userId") Long userId);
}