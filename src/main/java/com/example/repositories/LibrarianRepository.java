package com.example.repositories;

import com.example.entities.Librarian;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}