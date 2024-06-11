package ru.otus.pro.kovaleva;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ProxyUserService proxyUserService = new ProxyUserService(new UserServiceImpl());
            proxyUserService.register("login04", "password04", "username04");
            proxyUserService.getUsernameByLoginAndPassword("login01", "password01");
            proxyUserService.delete("login03");
            proxyUserService.closeUserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
