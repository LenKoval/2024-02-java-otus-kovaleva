package ru.otus.pro.kovaleva.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.pro.kovaleva.dto.ProductDto;
import ru.otus.pro.kovaleva.entities.Product;
import ru.otus.pro.kovaleva.exceptions.EntityNotFoundException;
import ru.otus.pro.kovaleva.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
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
    public Product createProduct(@RequestBody ProductDto productDto) throws EntityNotFoundException {
        logger.info("New product request title: " + productDto.getTitle() + " price: " + productDto.getPrice());
        return productService.createNewProduct(productDto);
    }
}
