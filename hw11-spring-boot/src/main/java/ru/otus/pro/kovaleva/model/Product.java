package ru.otus.pro.kovaleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String id;
    private String title;
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }
}
