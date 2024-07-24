package ru.otus.pro.kovaleva.services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.models.Product;
import ru.otus.pro.kovaleva.util.EntityUtil;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements EntityService<Product> {

    private SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);


    @Override
    public Product save(String name, String price) {
        Product product = new Product();
        product.setName(name);
        try {
            product.setPrice(Double.parseDouble(price));
        } catch (NumberFormatException e) {
            logger.error("Invalid price format: {}", price, e);
            throw e;
        }

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
    public boolean deleteById(Long id) {
        return EntityUtil.deleteById(sessionFactory, Product.class, id);
    }
}
