package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.exceptions.ApplicationInitializationException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMigrator {
    private final DataSource dataSource;

    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate() {
        try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            String sql = readFile("init.sql");
            statement.execute(sql);
        } catch (SQLException e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }

    }

    private String readFile(String fileName) {
        String sql;
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            if (url != null) {
                Path path = Paths.get(url.toURI());
                sql = Files.readString(path, StandardCharsets.UTF_8);
                return sql;
            } else {
                throw new FileNotFoundException("File not found.");
            }
        } catch (IOException | URISyntaxException e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
    }
}
