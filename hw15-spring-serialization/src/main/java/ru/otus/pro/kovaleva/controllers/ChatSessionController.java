package ru.otus.pro.kovaleva.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.pro.kovaleva.dtos.ResultChatSessionDto;
import ru.otus.pro.kovaleva.entities.ChatSession;
import ru.otus.pro.kovaleva.services.ChatSessionService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sms")
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    private ObjectMapper objectMapper;

    private XmlMapper xmlMapper;

    @Autowired
    public ChatSessionController(ChatSessionService chatSessionService, ObjectMapper objectMapper, XmlMapper xmlMapper) {
        this.chatSessionService = chatSessionService;
        this.objectMapper = objectMapper;
        this.xmlMapper = xmlMapper;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createResponseEntity(@RequestParam("file") MultipartFile file,
                                            @RequestHeader("Accept") String acceptHeader) throws IOException {

        List<ChatSession> chatSessions = Collections
                .singletonList(objectMapper.readValue(file.getBytes(), ChatSession.class));

        List<ResultChatSessionDto> processedMessages = chatSessionService.getResult(chatSessions);

        return processFormat(acceptHeader, xmlMapper, processedMessages);
    }

    private static ResponseEntity<?> processFormat(String header, XmlMapper mapper, List<ResultChatSessionDto> list)
            throws JsonProcessingException {
        if (MediaType.APPLICATION_XML_VALUE.equals(header)) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(mapper.writeValueAsString(list));
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(list);
        }
    }
}
