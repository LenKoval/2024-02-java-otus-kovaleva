package ru.otus.pro.kovaleva.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.pro.kovaleva.DataSource;
import ru.otus.pro.kovaleva.dao.UserDao;

import java.sql.SQLException;
public class ProxyUserService implements UserService {
    private final DataSource dataSource;
    private final UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(ProxyUserService.class);

    public ProxyUserService(UserDao userDao) throws SQLException {
        this.userDao = userDao;
        this.dataSource = userDao.getDataSource();
    }

    @Override
    public boolean register(String login, String password, String username) throws SQLException {
        try {
            dataSource.getConnection().setAutoCommit(false);
            boolean result = userDao.insert(login, password, username);
            dataSource.getConnection().commit();
            return result;
        } catch (SQLException e) {
            dataSource.getConnection().rollback();
            throw e;
        }
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException {
        try {
            dataSource.getConnection().setAutoCommit(false);
            String result = userDao.select(login, password);
            dataSource.getConnection().commit();
            return result;
        } catch (SQLException e) {
            dataSource.getConnection().rollback();
            throw e;
        }
    }

    @Override
    public void deleteUser(String username) throws SQLException {
        try {
            dataSource.getConnection().setAutoCommit(false);
            userDao.delete(username);
            dataSource.getConnection().commit();
        } catch (SQLException e) {
            dataSource.getConnection().rollback();
            throw e;
        }
    }

    @Override
    public void close() throws SQLException {
        dataSource.getConnection().close();
        logger.info("Connection close.");
    }
}
