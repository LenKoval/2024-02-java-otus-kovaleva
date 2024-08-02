package ru.otus.pro.kovaleva.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.otus.pro.kovaleva.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByOrderByIdDesc();
}
