package ru.otus.pro.kovaleva.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatSessionDto {

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("chat_identifier")
    private String chatIdentifier;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    private List<MemberDto> members;

    private List<MessageDto> messages;

}
