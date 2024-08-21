package ru.otus.pro.kovaleva.services;

import org.springframework.stereotype.Service;
import ru.otus.pro.kovaleva.dtos.GroupChatSessionDto;
import ru.otus.pro.kovaleva.dtos.ResultChatSessionDto;
import ru.otus.pro.kovaleva.entities.ChatSession;
import ru.otus.pro.kovaleva.entities.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatSessionServiceImpl implements ChatSessionService {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    @Override
    public List<ResultChatSessionDto> getResult(List<ChatSession> chatSessions) {

        return chatSessions.stream()
                .flatMap(chatSession -> chatSession.getMessages().stream()
                        .map(message -> toDto(chatSession, message)))
                .collect(Collectors.groupingBy(GroupChatSessionDto::getBelongNumber))
                .entrySet().stream()
                .map(entry -> {
                    List<GroupChatSessionDto> sortedList = sorting(entry.getValue());
                    ResultChatSessionDto resultChatSessionDto = new ResultChatSessionDto();
                    resultChatSessionDto.setBelongNumber(entry.getKey());
                    resultChatSessionDto.setMessages(sortedList);
                    return resultChatSessionDto;
                })
                .toList();
    }

    private GroupChatSessionDto toDto(ChatSession chatSession, Message message) {
        GroupChatSessionDto chatSessionDto = new GroupChatSessionDto();
        chatSessionDto.setChatIdentifier(chatSession.getChatIdentifier());
        chatSessionDto.setMemberLastName(chatSession.getMembers().get(0).getLastName());
        chatSessionDto.setBelongNumber(message.getBelongNumber());
        chatSessionDto.setSendDate(message.getSendDate());
        chatSessionDto.setText(message.getText());
        return chatSessionDto;
    }

    private List<GroupChatSessionDto> sorting(List<GroupChatSessionDto> chatSessionDtos) {
        return chatSessionDtos.stream()
                .sorted(Comparator.comparing(dto -> LocalDateTime.parse(dto.getSendDate(), dateTimeFormatter)))
                .toList();
    }
}
