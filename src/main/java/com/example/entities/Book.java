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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank(message = "Book name is mandatory")
    @Size(max = 255, message = "Book name must be less than 255 characters")
    private String bookName;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 255, message = "Author name must be less than 255 characters")
    private String author;

    @Size(max = 255, message = "Publication must be less than 255 characters")
    private String publication;

    @NotNull(message = "Availability is mandatory")
    private Boolean availability;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 255, message = "Category must be less than 255 characters")
    private String category;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
