package ru.otus.pro.kovaleva.service;

import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.Cart;
import ru.otus.pro.kovaleva.ProductRepository;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository repository;

    public CartServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart getNewCart() {
        return null;
    }

    @Override
    public void addProduct(Cart cart, String name, Integer quantity) {
        cart.addProduct(name, quantity);
    }

    @Override
    public void deleteProduct(Cart cart, Long id) {
        cart.deleteProduct(id);
    }
}
