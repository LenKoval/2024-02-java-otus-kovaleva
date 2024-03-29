package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.an.AfterSuite;
import ru.otus.pro.kovaleva.an.BeforeSuite;
import ru.otus.pro.kovaleva.an.Test;

public class TestClass {

    @BeforeSuite
    public void before() {
        System.out.println("test run - before");
    }

    @AfterSuite
    public void after() {
        System.out.println("test run - after");
    }

    @Test(priority = 2)
    public void test1() {
        System.out.println("test run - test1");
    }

    @Test(priority = 5)
    public void test2() {
        System.out.println("test run - test2");
    }
    @Test(priority = 8)
    public void test3() {
        System.out.println("test run - test3");
    }

    @Test
    public void exTest() {
        throw new RuntimeException();
    }
}
