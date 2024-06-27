package ru.otus.pro.kovaleva.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final List<Product> productList = new LinkedList<>();
    public List<Product> getList() {
        return productList;
    }

    public void addProduct(Product product) {
        if (product != null) {
            productList.add(product);
        }
    }

    public void deleteProduct(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                productList.remove(p);
            }
        }
    }
}
