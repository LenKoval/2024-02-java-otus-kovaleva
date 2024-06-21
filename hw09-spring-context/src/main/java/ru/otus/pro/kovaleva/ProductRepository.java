package ru.otus.pro.kovaleva;

import org.springframework.stereotype.Component;
import ru.otus.pro.kovaleva.model.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private final List<Product> productList = new LinkedList<>();

    {
        productList.add(new Product(1L, "Product 1", 248));
        productList.add(new Product(2L, "Product 2", 10));
        productList.add(new Product(3L, "Product 3", 2547));
        productList.add(new Product(4L, "Product 4", 48));
        productList.add(new Product(5L, "Product 5", 274));
        productList.add(new Product(6L, "Product 6", 42));
        productList.add(new Product(7L, "Product 7", 373));
        productList.add(new Product(8L, "Product 8", 22));
        productList.add(new Product(9L, "Product 9", 28));
        productList.add(new Product(10L, "Product 10", 236));
    }

    public List<String> findAll() {
        return productList.stream().map(Product::toString).collect(Collectors.toList());
    }

    public void save(Product product) {
        if (product.getId() == null) {
            Long id = productList.getLast().getId() + 1;
            product.setId(id);
        }
        productList.add(product);
    }

    public Product findById(Long id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        productList.remove(findById(id));
    }
}
