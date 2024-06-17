package ru.otus.pro.kovaleva.service;

import java.sql.SQLException;

public interface UserService {
    public boolean register(String login, String password, String username) throws SQLException;
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException;
    public void deleteUser(String username) throws SQLException;
    public void close() throws SQLException;
}
