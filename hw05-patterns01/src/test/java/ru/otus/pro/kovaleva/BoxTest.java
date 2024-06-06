package ru.otus.pro.kovaleva;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    @Test
    public void testLists() {
        Box box = new Box();
        box.getOne().add("One");
        box.getTwo().add("Two");
        box.getThree().add("Three");
        box.getFour().add("Four");

        Iterator<String> iterator = box.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "One");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "Two");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "Three");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "Four");
    }
    @Test
    public void testEmpty() {
        Box box = new Box();
        Iterator<String> iterator = box.iterator();
        assertFalse(iterator.hasNext());
    }
}
