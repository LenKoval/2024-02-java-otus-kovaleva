package ru.otus.pro.kovaleva.service;

import ru.otus.pro.kovaleva.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    void save(Product product);
    Product getProductById(int id);
    void deleteProduct(int id);
}
