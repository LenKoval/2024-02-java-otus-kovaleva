package ru.otus.pro.kovaleva.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.model.Product;
import ru.otus.pro.kovaleva.repository.ProductRepositoryImpl;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepository;
    private static Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    public void setProductRepository(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
