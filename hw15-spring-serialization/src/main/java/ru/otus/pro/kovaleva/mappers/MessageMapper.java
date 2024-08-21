package ru.otus.pro.kovaleva.mappers;

import ru.otus.pro.kovaleva.dtos.MessageDto;
import ru.otus.pro.kovaleva.entities.Message;

public class MessageMapper {

    public MessageDto toDto(Message message) {
        return new MessageDto(message.getRowId(),
                message.getAttributedBody(),
                message.getBelongNumber(),
                message.getDate(),
                message.getDateRead(),
                message.getGuid(),
                message.getHandleId(),
                message.getHasDdResults(),
                message.isDeleted(),
                message.getIsFromMe(),
                message.getSendDate(),
                message.getSendStatus(),
                message.getService(),
                message.getText());
    }
}
