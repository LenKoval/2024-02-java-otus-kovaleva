package ru.otus.pro.kovaleva.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EntityUtilHibernate<T> implements EntityUtil<T> {
    private static final Logger logger = LogManager.getLogger(EntityUtilHibernate.class);

    @Override
    public <T> T insert(SessionFactory sessionFactory, T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T merged = session.merge(entity);
            transaction.commit();
            logger.info("Entity saved: {}", entity);
            return merged;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error during entity insertion", e);
            throw e;
        }
    }

    @Override
    public <T> T findOneById(SessionFactory sessionFactory, Class<T> cls, long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T get = session.get(cls, id);
            logger.info("Entity get: {}", get);
            return get;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error during getting entity", e);
            throw e;
        }
    }

    @Override
    public <T> List<T> findAll(SessionFactory sessionFactory, Class<T> cls) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<T> result = session.createQuery("SELECT a FROM " + cls.getSimpleName() + " a", cls).getResultList();
            logger.info("List entities get: {}", result);
            return result;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error during getting list entities", e);
            throw e;
        }
    }

    @Override
    public <T> boolean deleteById(SessionFactory sessionFactory, Class<T> cls, long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(cls, id));
            transaction.commit();
            logger.info("Entity with id = " + id + " was removed.");
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error during remove entity", e);
            throw e;
        }
    }
}
