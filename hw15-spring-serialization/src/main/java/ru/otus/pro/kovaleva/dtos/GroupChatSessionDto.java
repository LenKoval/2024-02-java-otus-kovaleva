package ru.otus.pro.kovaleva.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupChatSessionDto {

    private String chatIdentifier;

    private String memberLastName;

    private String belongNumber;

    private String sendDate;

    private String text;
}
