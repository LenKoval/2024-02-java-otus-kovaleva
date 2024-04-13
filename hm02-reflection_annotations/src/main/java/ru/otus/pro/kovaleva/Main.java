package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.ex.TestException;

public class Main {
    public static void main(String[] args) {
        try {
            TestRunning.start(TestClass.class);
        } catch (TestException e) {
            System.out.println(e.getMessage());
        }
    }
}
