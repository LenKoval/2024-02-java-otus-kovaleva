package ru.otus.pro.kovaleva;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String DATABASE_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private final Connection connection;

    public UserServiceImpl() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        this.connection.setAutoCommit(false);
        createTable();
        addUsers();
    }

    @Override
    public boolean register(String login, String password, String username) throws SQLException {
        if (!checkRegisterStatus(login, password)) {
            String sql = "INSERT INTO users (login, password, username) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, username);
                ps.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
            }
        }
        return false;
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException {
        String sql = "SELECT username FROM users WHERE login = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        }
        return null;
    }

    @Override
    public boolean delete(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
        }
        return false;
    }

    @Override
    public void closeUserService() throws SQLException {
        connection.close();
    }

    private void createTable() throws SQLException {
        String sql = """
                CREATE TABLE users (
                    login VARCHAR(255) PRIMARY KEY,
                    password VARCHAR(255) NOT NULL,
                    username VARCHAR(255) NOT NULL
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void addUsers() throws SQLException {
        List<User> users = List.of(
                new User("log01", "pas01", "name01"),
                new User("log02", "pas02", "name02"),
                new User("log03", "pas03", "name03")
        );

        String sql = "INSERT INTO users (login, password, username) VALUES (?, ?, ?, ?)";

        insertUsers(sql, users);
    }
    private void insertUsers(String sql, List<User> users) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (User user : users) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getUsername());
                ps.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private boolean checkRegisterStatus(String login, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? OR password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        }
        return false;
    }
}
