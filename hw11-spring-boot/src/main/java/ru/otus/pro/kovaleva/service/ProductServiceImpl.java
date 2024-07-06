package ru.otus.pro.kovaleva.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.exceptions.ProductAppException;
import ru.otus.pro.kovaleva.dtos.ProductDto;
import ru.otus.pro.kovaleva.model.Product;
import ru.otus.pro.kovaleva.repositories.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getAllProducts() {
        return repository.findAllProducts();
    }

    @Override
    public Product getProductById(String id) {
        return repository.findProductById(id).orElse(null);
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws ProductAppException {
        if (productDto.getTitle().isEmpty() || productDto.getPrice() == 0) {
            logger.info("ProductDto is empty.");
            throw new ProductAppException("The title of the product cannot be empty / " +
                    "The price of the product must be greater than zero.");
        }
        logger.info("New product " + productDto.getTitle() + " price = " + productDto.getPrice());
        return repository.save(new Product(null, productDto.getTitle(), productDto.getPrice()));
    }
}
