package ru.otus.pro.kovaleva;

import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.configurations.JavaBasedSessionFactory;
import ru.otus.pro.kovaleva.models.Customer;
import ru.otus.pro.kovaleva.models.Product;
import ru.otus.pro.kovaleva.services.*;

public class MainApp {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = JavaBasedSessionFactory.getSessionFactory()) {
            EntityService<Customer> customerService = new CustomerServiceImpl(sessionFactory);
            EntityService<Product> productService = new ProductServiceImpl(sessionFactory);
            OrderManagementProcessor managementProcessor = new OrderManagementProcessorImpl(customerService, productService);
            managementProcessor.run();
        }
    }
}
