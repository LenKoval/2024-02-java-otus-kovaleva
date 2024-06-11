package ru.otus.pro.kovaleva;

import java.sql.SQLException;

public interface UserService {
    public boolean register(String login, String password, String username) throws SQLException;
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException;
    public boolean delete(String username) throws SQLException;
    public void closeUserService() throws SQLException;
}
