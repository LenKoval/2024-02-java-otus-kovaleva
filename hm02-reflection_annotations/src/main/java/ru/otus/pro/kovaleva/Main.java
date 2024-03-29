package ru.otus.pro.kovaleva;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestRunning.start(TestClass.class);
    }
}
