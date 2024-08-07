package ru.otus.pro.kovaleva.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Order> orders;

    @Override
    public String toString() {
        return String.format("Product id = %s, name = %s, price = %s", id, name, price);
    }
}
