package ru.otus.pro.kovaleva.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final List<Product> productList = new LinkedList<>();
    private static Logger logger = LogManager.getLogger(Cart.class);
    public List<Product> getList() {
        return productList;
    }

    public void addProduct(Product product) {
        if (product != null) {
            productList.add(product);
            logger.info("Add " + product.getName() + " product.");
        }
    }

    public void deleteProduct(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                logger.info("product id = " + id + "found.");
                productList.remove(p);
            }
            logger.info("Product id = " + id + " not found.");
        }
    }
}
