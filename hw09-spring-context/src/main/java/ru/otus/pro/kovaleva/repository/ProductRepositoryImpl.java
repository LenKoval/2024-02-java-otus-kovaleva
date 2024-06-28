package ru.otus.pro.kovaleva.repository;

import org.springframework.stereotype.Component;
import ru.otus.pro.kovaleva.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1, "Product1", 248));
        productList.add(new Product(2, "Product2", 10));
        productList.add(new Product(3, "Product3", 2547));
        productList.add(new Product(4, "Product4", 48));
        productList.add(new Product(5, "Product5", 274));
        productList.add(new Product(6, "Product6", 42));
        productList.add(new Product(7, "Product7", 373));
        productList.add(new Product(8, "Product8", 22));
        productList.add(new Product(9, "Product9", 28));
        productList.add(new Product(10, "Product10", 236));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) {
            int id = productList.get(productList.size() - 1).getId() + 1;
            product.setId(id);
        }
        productList.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (findById(id) != null) {
            productList.remove(id);
        }
    }
}
