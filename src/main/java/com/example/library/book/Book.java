package com.example.library.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Samson Effes
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name="title")
    private String title;

    private String author;

    private String edition;

    private UUID isbn = UUID.randomUUID();




}