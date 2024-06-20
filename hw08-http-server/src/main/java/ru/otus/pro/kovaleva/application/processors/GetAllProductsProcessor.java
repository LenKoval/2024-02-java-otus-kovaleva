package ru.otus.pro.kovaleva.application.processors;

import com.google.gson.Gson;
import ru.otus.pro.kovaleva.HttpRequest;
import ru.otus.pro.kovaleva.application.Item;
import ru.otus.pro.kovaleva.application.Storage;
import ru.otus.pro.kovaleva.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetAllProductsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        List<Item> items = Storage.getItems();
        Gson gson = new Gson();
        String result = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: keep-alive\r\n" +
                "Access-Control-Allow-Origin: *\r\n\r\n" + gson.toJson(items);
        output.write(result.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }
}
