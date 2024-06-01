package ru.otus.pro.kovaleva.for_remove;

import ru.otus.pro.kovaleva.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
    List<User> findAll() throws SQLException;
}
