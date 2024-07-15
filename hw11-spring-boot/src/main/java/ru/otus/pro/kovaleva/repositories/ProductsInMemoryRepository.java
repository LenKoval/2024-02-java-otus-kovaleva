package ru.otus.pro.kovaleva.repositories;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.pro.kovaleva.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Component
public class ProductsInMemoryRepository implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    private final static Logger logger = LoggerFactory.getLogger(ProductsInMemoryRepository.class.getName());

    @PostConstruct
    public void create() {
        products.add(new Product("1", "Product1", 12.22));
        products.add(new Product("2", "Product2", 43.31));
        products.add(new Product("3", "Product3", 12.31));
        products.add(new Product("4", "Product4", 11.32));
        products.add(new Product("5", "Product5", 12.43));
        products.add(new Product("6", "Product6", 13.78));
        logger.info("Create repository.");
    }

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public Product save(Product product) {
        int id = Integer.parseInt(products.getLast().getId()) + 1;
        product.setId(Integer.toString(id));
        products.add(product);
        logger.info("Save new product id = " + product.getId());
        return product;
    }
}
