package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private double sum;

    public BookItem(String title, String author, double price, int quantity, double sum) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }

    public BookItem(Integer id, String title, String author, double price, int quantity, double sum) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
        this.id=id;
    }

    @ManyToOne
    private Customer customer;

}
