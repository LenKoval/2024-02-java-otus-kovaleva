package ru.otus.pro.kovaleva.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultChatSessionDto {

    private String belongNumber;

    private List<GroupChatSessionDto> messages;
}
