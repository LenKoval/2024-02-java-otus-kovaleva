package ru.otus.pro.kovaleva;

import lombok.AllArgsConstructor;

import java.sql.SQLException;
@AllArgsConstructor
public class ProxyUserService implements UserService {
    private final UserServiceImpl userService;


    @Override
    public boolean register(String login, String password, String username) throws SQLException {
        return userService.register(login, password, username);
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException {
        return userService.getUsernameByLoginAndPassword(login, password);
    }

    @Override
    public boolean delete(String username) throws SQLException {
        return userService.delete(username);
    }

    @Override
    public void closeUserService() throws SQLException {
        userService.closeUserService();
    }
}
