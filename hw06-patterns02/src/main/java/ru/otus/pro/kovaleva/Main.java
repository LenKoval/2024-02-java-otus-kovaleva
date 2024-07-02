package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.dao.UserDao;
import ru.otus.pro.kovaleva.service.ProxyUserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ProxyUserService proxyUserService = new ProxyUserService(new UserDao(new DataSource("jdbc:h2:mem:testdb", "sa", "")));
            proxyUserService.register("login04", "password04", "username04");
            proxyUserService.getUsernameByLoginAndPassword("login01", "password01");
            proxyUserService.deleteUser("login03");
            proxyUserService.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
