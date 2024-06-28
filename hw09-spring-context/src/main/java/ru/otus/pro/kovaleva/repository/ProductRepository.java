package ru.otus.pro.kovaleva.repository;

import ru.otus.pro.kovaleva.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);

    Product findById(int id);
    void deleteById(int id);
}
