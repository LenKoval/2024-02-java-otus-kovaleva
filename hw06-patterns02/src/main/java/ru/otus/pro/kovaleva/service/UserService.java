package ru.otus.pro.kovaleva.service;

import java.sql.SQLException;

public interface UserService {
    boolean register(String login, String password, String username) throws SQLException;
    String getUsernameByLoginAndPassword(String login, String password) throws SQLException;
    void deleteUser(String username) throws SQLException;
    void close() throws SQLException;
}
