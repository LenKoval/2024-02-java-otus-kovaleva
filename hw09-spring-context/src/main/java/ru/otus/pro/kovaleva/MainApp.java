package ru.otus.pro.kovaleva;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.pro.kovaleva.service.CartManagementProcessor;
import ru.otus.pro.kovaleva.service.CartManagementProcessorImpl;

@ComponentScan({"ru.otus.pro.kovaleva"})
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);
        CartManagementProcessor processor = context.getBean(CartManagementProcessorImpl.class);
        processor.run();
    }
}
