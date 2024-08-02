package ru.otus.pro.kovaleva.services;

import ru.otus.pro.kovaleva.dto.ProductDto;
import ru.otus.pro.kovaleva.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createNewProduct(ProductDto productDto);
}
