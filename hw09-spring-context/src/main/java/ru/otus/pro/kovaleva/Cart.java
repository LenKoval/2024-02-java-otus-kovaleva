package ru.otus.pro.kovaleva;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.pro.kovaleva.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Component
//@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(String name, Integer quantity) {
        productRepository.save(new Product(null, name, quantity));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
