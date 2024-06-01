package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.exceptions.ApplicationInitializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractRepository<T> {
    private final DataSource dataSource;

    private PreparedStatement psCreate;

    private List<Field> cachedFields;
    private List<Method> cachedSetters;
    private List<Method> cachedGetters;
    private List<String> fields;

    private final Class<T> cls;
    String tableName;

    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        prepare(cls);
    }

    public void create(T entity) {
        try {
            for (int i = 0; i < cachedFields.size(); i++) {
                psCreate.setObject(i + 1, cachedFields.get(i).get(entity));
            }
            psCreate.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationInitializationException();
        }
    }

    private void prepare(Class<T> cls) {
        StringBuilder query = new StringBuilder("insert into ");
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        query.append(tableName).append(" (");
        // 'insert into users ('
        cachedFields = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RepositoryField.class))
                .filter(f -> !f.isAnnotationPresent(RepositoryIdField.class))
                .collect(Collectors.toList());
        for (Field f : cachedFields) { // TODO Сделать использование геттеров
            f.setAccessible(true);
        }
        for (Field f : cachedFields) {
            query.append(f.getName()).append(", ");
        }
        // 'insert into users (login, password, nickname, '
        query.setLength(query.length() - 2);
        // 'insert into users (login, password, nickname'
        query.append(") values (");
        for (Field f : cachedFields) {
            query.append("?, ");
        }
        // 'insert into users (login, password, nickname) values (?, ?, ?, '
        query.setLength(query.length() - 2);
        // 'insert into users (login, password, nickname) values (?, ?, ?'
        query.append(");");
        try {
            psCreate = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
    }

    public T findById(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(String.format("select * from %s where id = ?", tableName))) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T obj = getObjFromResultSet(resultSet);
                return obj;
            }
            return null;
        } catch (Exception e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = dataSource.getConnection()
                .prepareStatement(String.format("select * from %s", tableName));
            ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                T obj = getObjFromResultSet(resultSet);
                list.add(obj);
            }
        } catch (Exception e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
        return list;
    }

    private T getObjFromResultSet(ResultSet resultSet) throws Exception {
        T obj = cls.getDeclaredConstructor().newInstance();
        for (int i = 0; i < fields.size(); i++) {
            String fieldName = fields.get(i);
            Method setter = cachedSetters.get(i);
            setter.invoke(obj, resultSet.getObject(fieldName));
        }
        Method setId = cls.getMethod("setId", Long.class);
        setId.invoke(obj, resultSet.getLong("id"));
        return obj;
    }

    public void update(T entity) {

    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(String.format("delete from %s where id = ?", tableName))) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
    }

    public void deleteAll() {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(String.format("delete from %s where id = ?", tableName))) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationInitializationException(e.getMessage(), e);
        }
    }


}
