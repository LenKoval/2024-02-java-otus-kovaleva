package ru.otus.pro.kovaleva.service;

import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.ProductRepository;
import ru.otus.pro.kovaleva.model.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    @Override
    public List<String> getProductList() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
