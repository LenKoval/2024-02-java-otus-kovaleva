package ru.otus.pro.kovaleva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {
    @Test
    public void checkSimpleTest() {
        Product product1 = Product.builder()
                .id(10)
                .title("title")
                .description("description")
                .cost(20)
                .weight(5)
                .length(15)
                .height(7)
                .build();

        Product product2 = Product.builder().build();


        assertEquals(product1.getTitle(), "title");
        assertEquals(product1.getWeight(), 5);
        assertNotEquals(product1, product2);
        assertEquals(null, product2.getTitle());
    }
}
