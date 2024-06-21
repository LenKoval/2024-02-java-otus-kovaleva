package ru.otus.pro.kovaleva.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private int price;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("Product {id = %-2s | name = %-15s | price = %-8s}", id, name, price);
    }
}
