package ru.otus.pro.kovaleva.exceptions;

public class ApplicationInitializationException extends RuntimeException {
    public ApplicationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
