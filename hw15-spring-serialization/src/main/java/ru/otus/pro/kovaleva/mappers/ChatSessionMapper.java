package ru.otus.pro.kovaleva.mappers;

import ru.otus.pro.kovaleva.dtos.ChatSessionDto;
import ru.otus.pro.kovaleva.entities.ChatSession;

public class ChatSessionMapper {

    private MemberMapper memberMapper;

    private MessageMapper messageMapper;

    public ChatSessionDto toDto(ChatSession chatSession) {
        return new ChatSessionDto(chatSession.getChatId(),
                chatSession.getChatIdentifier(),
                chatSession.getDisplayName(),
                chatSession.isDeleted(),
                chatSession.getMembers().stream()
                        .map(memberMapper::toDto)
                        .toList(),
                chatSession.getMessages().stream()
                        .map(messageMapper::toDto)
                        .toList());
    }
}
