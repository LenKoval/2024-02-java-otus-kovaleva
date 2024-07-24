package ru.otus.pro.kovaleva.services;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.models.Customer;
import ru.otus.pro.kovaleva.models.Order;
import ru.otus.pro.kovaleva.models.Product;
import ru.otus.pro.kovaleva.util.EntityUtil;

import java.util.List;

@AllArgsConstructor
public class CustomerServiceImpl implements EntityService<Customer> {

    private SessionFactory sessionFactory;


    @Override
    public Customer save(String name, String id) {
        Customer customer = new Customer();
        customer.setName(name);
        Product product = EntityUtil.findOneById(sessionFactory, Product.class, Long.parseLong(id));
        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setOrderPrice(product.getPrice());
        EntityUtil.insert(sessionFactory, order);
        return customer;
    }

    @Override
    public List<Customer> printAll() {
        return EntityUtil.findAll(sessionFactory, Customer.class);
    }

    @Override
    public Customer printById(Long id) {
        return EntityUtil.findOneById(sessionFactory, Customer.class, id);
    }

    @Override
    public boolean deleteById(Long id) {
        return EntityUtil.deleteById(sessionFactory, Customer.class, id);
    }
}
