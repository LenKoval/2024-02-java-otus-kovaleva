package ru.otus.pro.kovaleva.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.pro.kovaleva.models.Customer;
import ru.otus.pro.kovaleva.models.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class OrderManagementProcessorImpl implements OrderManagementProcessor {
    private final EntityService<Customer> customerService;

    private final EntityService<Product> productService;

    private boolean exit;

    private static final Logger logger = LogManager.getLogger(OrderManagementProcessorImpl.class);


    @Override
    public void run() {
            System.out.println("ORDER MANAGEMENT");
            printCommand();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                exit = false;
                while (!exit) {
                    System.out.println("Enter the command...");

                    String str = br.readLine();
                    handleMessage(str);
                }
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
            logger.info("Application close.");
    }

    private void handleMessage(String message) {
        if (!message.isEmpty()) {
            String[] parts = message.split("\\s");
            String command = parts[0];

            switch (command) {
                case "exit" :
                    System.out.println("Cart management is closed.");
                    exit = true;
                case "/?" :
                    printCommand();
                    break;
                case "newProduct" :
                    productService.save(parts[1], parts[2]);
                    break;
                case "listProduct" :
                    printList(productService.printAll());
                    break;
                case "newCustomer" :
                    customerService.save(parts[1], parts[2]);
                    break;
                case "listCustomer" :
                    printList(customerService.printAll());
                    break;
                case "products" :
                    System.out.println(customerService.printById(Long.parseLong(parts[1])));
                    break;
                case "customers" :
                    System.out.println(productService.printById(Long.parseLong(parts[1])));
                    break;
                case "delProduct" :
                    productService.deleteById(Long.parseLong(parts[1]));
                    break;
                case "delCustomer" :
                    customerService.deleteById(Long.parseLong(parts[1]));
                    break;
                default: {
                    printCommand();
                }
            }
        }
    }

    private static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    private static void printCommand() {
        System.out.println("Create a new product: newProduct [product name] [price]");
        System.out.println("Print all products: listProduct");
        System.out.println("Create a new customer: newCustomer [customer name] [product id]");
        System.out.println("Print all customers: listCustomer");
        System.out.println("Find all products purchased by a customer: products");
        System.out.println("Find all customers who bought a certain product: customers");
        System.out.println("Remove product: delProduct [product id]");
        System.out.println("Remove customer: delCustomer [customer id]");
        System.out.println("Finish: exit");
    }
}
