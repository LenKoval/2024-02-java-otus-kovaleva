package ru.otus.pro.kovaleva.processors;

import ru.otus.pro.kovaleva.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest httpRequest, OutputStream output) throws IOException;
}
