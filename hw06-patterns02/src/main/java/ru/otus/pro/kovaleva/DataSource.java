package ru.otus.pro.kovaleva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static Connection connection;

    public DataSource() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    }

    public Connection getConnection() {
        return connection;
    }
}
