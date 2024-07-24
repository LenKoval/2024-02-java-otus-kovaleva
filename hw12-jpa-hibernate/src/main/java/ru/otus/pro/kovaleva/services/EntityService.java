package ru.otus.pro.kovaleva.services;

import java.util.List;

public interface EntityService<T> {
    T save(String str1, String str2);

    List<T> printAll();

    T printById(Long id);

    boolean deleteById(Long id);

}
