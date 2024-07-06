package ru.otus.pro.kovaleva.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.pro.kovaleva.exceptions.ProductAppException;
import ru.otus.pro.kovaleva.dtos.ProductDto;
import ru.otus.pro.kovaleva.model.Product;
import ru.otus.pro.kovaleva.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("{id}")
    public Product getProductById(@PathVariable String id){
        logger.info("Product request by id = " + id);
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("All products request.");
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductDto productDto) throws ProductAppException {
        logger.info("New product request title: " + productDto.getTitle() + " price: " + productDto.getPrice());
        return productService.createNewProduct(productDto);
    }
}
