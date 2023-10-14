package com.example.bootcruds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String imgUrl;
    private String publisher;
    @ManyToOne
    private Author author;
}
