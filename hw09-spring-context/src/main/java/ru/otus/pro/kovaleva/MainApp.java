package ru.otus.pro.kovaleva;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan({"ru.otus.pro.kovaleva"})
public class MainApp {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);
        CartRunner cartRunner = context.getBean(CartRunner.class);
        cartRunner.run();
    }
}
