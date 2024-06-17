package ru.otus.pro.kovaleva.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.pro.kovaleva.DataSource;
import ru.otus.pro.kovaleva.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDao {
    private final DataSource dataSource;
    private final PreparedStatement insertPS;
    private final PreparedStatement selectPS;
    private final PreparedStatement deletePS;
    private final PreparedStatement selectCountPS;

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);


    public UserDao(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        createTable();
        this.insertPS = this.dataSource.getConnection().prepareStatement("INSERT INTO users (login, password, username) VALUES (?, ?, ?)");
        addUsers();
        this.selectPS = this.dataSource.getConnection().prepareStatement("SELECT username FROM users WHERE login = ? AND password = ?");
        this.deletePS = this.dataSource.getConnection().prepareStatement("DELETE FROM users WHERE username = (?)");
        this.selectCountPS = this.dataSource.getConnection().prepareStatement("SELECT COUNT(*) FROM users WHERE login = ? OR password = ?");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public boolean insert(String login, String password, String username) {
        if (!checkRegisterStatus(login, password)) {
            try {
                insertPS.setString(1, login);
                insertPS.setString(2, password);
                insertPS.setString(3, username);
                insertPS.executeUpdate();
                return true;
            } catch (SQLException e) {
                logger.error("Error register: " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    public String select(String login, String password) {
        try {
            selectPS.setString(1, login);
            selectPS.setString(2, password);
            ResultSet rs = selectPS.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            logger.error("Get username by login and password exception: " + e.getLocalizedMessage());
        }
        return null;
    }

    public void delete(String username) throws SQLException {
        deletePS.setString(1, username);
        deletePS.executeUpdate();
        logger.info("Execute " + deletePS);
        logger.info("Delete " + username);
    }

    private void createTable() throws SQLException {
        String sql = """
                CREATE TABLE users (
                    login VARCHAR(255) PRIMARY KEY,
                    password VARCHAR(255) NOT NULL,
                    username VARCHAR(255) NOT NULL
                );
                """;
        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.execute(sql);
        }
    }

    private void addUsers() throws SQLException {
        List<User> users = List.of(
                new User("log01", "pas01", "name01"),
                new User("log02", "pas02", "name02"),
                new User("log03", "pas03", "name03")
        );

        insertUsers(users);
    }
    private void insertUsers(List<User> users) throws SQLException {
        try {
            for (User user : users) {
                insertPS.setString(1, user.getLogin());
                insertPS.setString(2, user.getPassword());
                insertPS.setString(3, user.getUsername());
                insertPS.executeBatch();
            }
            dataSource.getConnection().commit();
        } catch (SQLException e) {
            dataSource.getConnection().rollback();
        }
    }

    private boolean checkRegisterStatus(String login, String password) {
        try {
            selectCountPS.setString(1, login);
            selectCountPS.setString(2, password);
            ResultSet rs = selectCountPS.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
