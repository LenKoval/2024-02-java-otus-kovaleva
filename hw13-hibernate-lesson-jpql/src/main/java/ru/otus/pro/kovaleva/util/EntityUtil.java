package ru.otus.pro.kovaleva.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public interface EntityUtil<T> {

    <T> T insert(SessionFactory sessionFactory, T entity);

    <T> T findOneById(SessionFactory sessionFactory, Class<T> cls, long id);

    <T> List<T> findAll(SessionFactory sessionFactory, Class<T> cls);

    <T> boolean deleteById(SessionFactory sessionFactory, Class<T> cls, long id);
}
