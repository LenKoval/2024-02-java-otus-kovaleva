package ru.otus.pro.kovaleva.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.models.Product;
import ru.otus.pro.kovaleva.util.EntityUtil;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements EntityService<Product> {

    private final SessionFactory sessionFactory;


    @Override
    public Product save(String name, String price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        EntityUtil.insert(sessionFactory, product);
        return product;
    }

    @Override
    public List<Product> printAll() {
        return EntityUtil.findAll(sessionFactory, Product.class);
    }

    @Override
    public Product printById(Long id) {
        return EntityUtil.findOneById(sessionFactory, Product.class, id);
    }

    @Override
    public void deleteById(Long id) {
        EntityUtil.deleteById(sessionFactory, Product.class, id);
    }
}
