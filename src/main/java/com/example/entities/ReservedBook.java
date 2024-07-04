package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table
public class ReservedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    private Users user;

    @NotNull(message = "BorrowedAt date and time is mandatory")
    @PastOrPresent(message = "BorrowedAt must be a date and time in the present or past")
    private LocalDate borrowedAt;

    @NotNull(message = "Deadline date and time is mandatory")
    @FutureOrPresent(message = "Deadline must be a date and time in the present or future")
    private LocalDate deadline;
}