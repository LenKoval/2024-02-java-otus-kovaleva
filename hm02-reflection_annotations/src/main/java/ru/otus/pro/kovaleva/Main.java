package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.ex.TestException;

public class Main {
    public static void main(String[] args) throws TestException {
        TestRunning.start(TestClass.class);
    }
}
