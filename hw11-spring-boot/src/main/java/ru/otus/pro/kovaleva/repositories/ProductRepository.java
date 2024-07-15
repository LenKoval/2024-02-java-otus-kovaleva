package ru.otus.pro.kovaleva.repositories;

import ru.otus.pro.kovaleva.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findProductById(String id);

    Product save(Product product);


}
