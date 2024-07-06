package ru.otus.pro.kovaleva.service;

import ru.otus.pro.kovaleva.exceptions.ProductAppException;
import ru.otus.pro.kovaleva.dtos.ProductDto;
import ru.otus.pro.kovaleva.model.Product;

import java.util.List;

public interface ProductService {
    List getAllProducts();

    Product getProductById(String id);

    Product createNewProduct(ProductDto productDto) throws ProductAppException;
}
