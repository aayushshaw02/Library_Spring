package com.example.entities;

import jakarta.persistence.*;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Getter
@Setter
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;

    private String author;
    private String publication;
    private Boolean availability;
    private String category;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
