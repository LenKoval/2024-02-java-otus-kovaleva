package ru.otus.pro.kovaleva.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import ru.otus.pro.kovaleva.model.Cart;
import ru.otus.pro.kovaleva.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Configuration
public class CartManagementProcessorImpl implements CartManagementProcessor {
    private final ProductService productService;
    private CartServiceImpl cartService;
    private Cart cart;
    private boolean exit;
    private static Logger logger = LogManager.getLogger(CartManagementProcessorImpl.class);

    public CartManagementProcessorImpl(ProductService productService, CartServiceImpl cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public void run() {
        logger.info("Application start.");
        cart = cartService.getNewCart();
        System.out.println("CART MANAGEMENT");
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
        int id = 0;
        if (!message.isEmpty()) {
            String[] parts = message.split("\\s");
            String command = parts[0];
            if (parts.length == 2) {
                id = Integer.parseInt(parts[1]);
            }

            switch (command) {
                case "exit" :
                    System.out.println("Cart management is closed.");
                    exit = true;
                case "/?" :
                    printCommand();
                    break;
                case "list" :
                    printListProducts(productService.getProductList());
                    break;
                case "cart" :
                    cartService.printCart(cart);
                    break;
                case "new" :
                    cart = cartService.getNewCart();
                    System.out.println("New Cart.");
                    break;
                case "find" :
                    System.out.println(productService.getProductById(id));
                    break;
                case "add" :
                    Product product = productService.getProductById(id);
                    if (product != null) {
                        cartService.addProduct(cart, productService.getProductById(id));
                        System.out.println("Product " + id + " was added in cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case "delete" :
                    cartService.delProduct(cart, productService.getProductById(id));
                    System.out.println("Product " + productService.getProductById(id) + " was removed.");
                    break;
                default: {
                    printCommand();
                }
            }
        }
    }

    private static void printListProducts(List<?> list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    private static void printCommand() {
        System.out.println("Print products list: list");
        System.out.println("Print products Cart: cart");
        System.out.println("Find product by ID: find [product id]");
        System.out.println("Add product: add [product id]");
        System.out.println("Remove product: delete [product id]");
        System.out.println("Create a new Cart: new");
        System.out.println("Finish: exit");
    }
}
