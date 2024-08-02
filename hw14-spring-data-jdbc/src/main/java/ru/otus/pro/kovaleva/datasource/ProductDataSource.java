package ru.otus.pro.kovaleva.datasource;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ProductDataSource {

    private final DataSource dataSource;

    private final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();

    private final ClassPathResource shema = new ClassPathResource("db/migration/shema.sql");

    private final ClassPathResource data = new ClassPathResource("db/migration/data.sql");

    private static final Logger logger = LoggerFactory.getLogger(ProductDataSource.class);

    @PostConstruct
    public void create() {
        databasePopulator.addScript(shema);
        databasePopulator.addScript(data);
        databasePopulator.execute(dataSource);
        logger.info("Create repository.");
    }
}
