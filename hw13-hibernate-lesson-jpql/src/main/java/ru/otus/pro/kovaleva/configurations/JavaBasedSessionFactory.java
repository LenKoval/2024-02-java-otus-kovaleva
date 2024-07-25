package ru.otus.pro.kovaleva.configurations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.pro.kovaleva.models.Address;
import ru.otus.pro.kovaleva.models.Client;
import ru.otus.pro.kovaleva.models.Phone;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaBasedSessionFactory {

    private static final Logger logger = LogManager.getLogger(JavaBasedSessionFactory.class);

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = createConfiguration();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            logger.info("Hibernate Java Config serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Configuration createConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = getHibernateProperties();

        configuration.setProperties(properties)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(Client.class);

        return configuration;
    }

    private static Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:~/test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.current_session_context_class", "thread");

        return properties;
    }
}
