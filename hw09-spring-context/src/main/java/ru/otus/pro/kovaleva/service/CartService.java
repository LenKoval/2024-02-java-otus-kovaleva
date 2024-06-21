package ru.otus.pro.kovaleva.service;

import ru.otus.pro.kovaleva.Cart;

public interface CartService {
    Cart getNewCart();

    void addProduct(Cart cart, String name, Integer quantity);

    void deleteProduct(Cart cart, Long id);
}
