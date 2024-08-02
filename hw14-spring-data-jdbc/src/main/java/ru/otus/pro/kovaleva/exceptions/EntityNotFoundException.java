package ru.otus.pro.kovaleva.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Product with id=" + id + " not found");
    }

    public EntityNotFoundException(String title) {
        super("Product with title=" + title + " not found");
    }
}
