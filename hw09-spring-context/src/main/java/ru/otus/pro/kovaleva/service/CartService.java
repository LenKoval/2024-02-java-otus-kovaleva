package ru.otus.pro.kovaleva.service;

import ru.otus.pro.kovaleva.model.Cart;
import ru.otus.pro.kovaleva.model.Product;

public interface CartService {
    Cart getNewCart();
    void addProduct(Cart cart, Product product);
    void addProduct(Cart cart, int id);
    void delProduct(Cart cart, Product product);
    void printCart(Cart cart);
}
