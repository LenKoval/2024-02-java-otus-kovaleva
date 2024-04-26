package ru.otus.pro.kovaleva;

import ru.otus.pro.kovaleva.an.AfterSuite;
import ru.otus.pro.kovaleva.an.BeforeSuite;
import ru.otus.pro.kovaleva.an.Test;
import ru.otus.pro.kovaleva.ex.TestException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TestRunning {
    private static int testsSuccess = 0;
    private static int testFailed = 0;

    public static <T> void start(Class<T> clazz) throws TestException {
        try {
            Constructor constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();


            startSingleMethod(clazz, BeforeSuite.class, instance);

            List<Method> tests = getMethodByAnnotation(clazz, Test.class);

            for (Method test : tests) {
                if (test.getAnnotation(Test.class).priority() < 1 || test.getAnnotation(Test.class).priority() > 10) {
                    throw new TestException("Tests cannot be completed!");
                }
            }
            tests.sort(Comparator.comparingInt((Method method) -> method.getAnnotation(Test.class).priority()).reversed());

            for (Method test : tests) {
                if (executeMethod(test, instance)) {
                    testsSuccess++;
                } else {
                    testFailed++;
                }
            }

            startSingleMethod(clazz, AfterSuite.class, instance);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new TestException("Tests cannot be completed!");
        }

        System.out.println("Результат : успешно " + testsSuccess + "; упало " + testFailed + "; всего " + (testsSuccess + testFailed) + ".");
    }

    private static List<Method> getMethodByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = Arrays
                .stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());

        return annotatedMethods;
    }

    private static void startSingleMethod(Class<?> clazz, Class<? extends Annotation> annotation, Object instance) throws TestException {
        List<Method> singleMethodList = getMethodByAnnotation(clazz, annotation);

        if (singleMethodList.size() > 1) {
            throw new TestException("Tests cannot be completed!");
        }

        for(Method method : singleMethodList) {
            if (executeMethod(method, instance)) {
                testsSuccess++;
            } else {
                testFailed++;
            }
        }

    }

    private static boolean executeMethod(Method method, Object instance) {
        boolean exTest = true;
        try {
            method.invoke(instance);
        } catch (Exception e) {
            exTest = false;
        }
        return exTest;
    }
}
