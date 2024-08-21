package ru.otus.pro.kovaleva.services;

import ru.otus.pro.kovaleva.dtos.ResultChatSessionDto;
import ru.otus.pro.kovaleva.entities.ChatSession;

import java.util.List;

public interface ChatSessionService {

    List<ResultChatSessionDto> getResult(List<ChatSession> chatSessions);
}
