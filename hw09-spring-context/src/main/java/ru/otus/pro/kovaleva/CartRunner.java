package ru.otus.pro.kovaleva;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CartRunner {
    private Cart cart;

    public CartRunner(Cart cart) {
        this.cart = cart;
    }

    public void run() throws IOException {
        System.out.println("CART MANAGEMENT");
        printCommand();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter the command...");

            Long id;

            String str = br.readLine();
            if (!str.isEmpty()) {
                String[] parts = str.split("\\s");
                String command = parts[0];

                if (command.equalsIgnoreCase("exit")) {
                    exit = true;
                    System.out.println("Cart management is closed.");
                } else if (command.equalsIgnoreCase("/?")) {
                    printCommand();
                } else if (command.equals("list")) {
                    printList(cart.getProducts());
                } else if (parts.length == 3 && command.equalsIgnoreCase("add")) {
                    cart.addProduct(parts[1], Integer.parseInt(parts[2]));
                    System.out.println("Product " + parts[1] + " quantity " + parts[2] + " was added in cart.");
                } else if (parts.length == 2 && command.equalsIgnoreCase("delete")) {
                    cart.deleteProduct(Long.parseLong(parts[1]));
                    System.out.println("Product removed.");
                } else {
                    System.out.println("Error.");
                }
            }
        }
    }

    private static void printList(List<?> list) {
        System.out.println("PRODUCT LIST");
        for (Object entity : list) {
            System.out.println(entity.toString());
        }
    }

    private static void printCommand() {
        System.out.println("Print product list: list");
        System.out.println("Add product: add [name product] [quantity]");
        System.out.println("Remove product: delete [product id]");
        System.out.println("Finish: exit");
    }
}
