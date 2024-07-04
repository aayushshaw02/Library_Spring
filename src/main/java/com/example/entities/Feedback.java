package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book ID is mandatory")
    @Size(max = 255, message = "Book ID must be less than 255 characters")
    private String bookId;

    @NotBlank(message = "Message is mandatory")
    @Size(max = 500, message = "Message must be less than 500 characters")
    private String message;

    @NotNull(message = "GeneratedAt date and time is mandatory")
    private String generatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    private Users users;
}
