package ru.otus.pro.kovaleva.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {

    @Id
    @Column("id")
    private Long id;

    @Column("title")
    private String title;

    @Column("price")
    private String price;

    public Product(String title, String price) {
        this.title = title;
        this.price = price;
    }
}
