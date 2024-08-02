package ru.otus.pro.kovaleva.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.dto.ProductDto;
import ru.otus.pro.kovaleva.entities.Product;
import ru.otus.pro.kovaleva.exceptions.EntityNotFoundException;
import ru.otus.pro.kovaleva.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getAllProducts() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            throw new EntityNotFoundException(id);
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws EntityNotFoundException {
        if (productDto.getTitle().isEmpty() || productDto.getPrice().isEmpty()) {
            logger.info("ProductDto is empty.");
            throw new EntityNotFoundException(productDto.getTitle());
        }
        logger.info("New product " + productDto.getTitle() + " price = " + productDto.getPrice());
        return repository.save(new Product(null, productDto.getTitle(), productDto.getPrice()));
    }
}
