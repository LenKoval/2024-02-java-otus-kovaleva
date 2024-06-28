package ru.otus.pro.kovaleva.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.model.Cart;
import ru.otus.pro.kovaleva.model.Product;
import ru.otus.pro.kovaleva.repository.ProductRepositoryImpl;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepositoryImpl productRepository;

    public CartServiceImpl(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    @Lookup
    @Override
    public Cart getNewCart() {
        return null;
    }

    @Override
    public void addProduct(Cart cart, Product product) {
        cart.addProduct(product);
    }

    @Override
    public void addProduct(Cart cart, int id) {
        Product product = productRepository.findById(id);
        this.addProduct(cart, product);
    }

    @Override
    public void delProduct(Cart cart, Product product) {
        cart.deleteProduct(product.getId());
    }

    @Override
    public void printCart(Cart cart) {
        for (Product product : cart.getList()) {
            System.out.printf("Product id = %-2s | name = %-15s | price = %-8s",
                    product.getId(), product.getName(), product.getPrice());
        }
    }
}
