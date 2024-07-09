package com.example.repositories;

import com.example.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("Select u from Users u where u.name = :name")
    Page<Users> findUsersByName(@Param("name") String name, PageRequest pageRequest);
}
