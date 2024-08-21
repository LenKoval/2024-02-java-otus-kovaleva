package ru.otus.pro.kovaleva.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDto {

    private long rowId;

    private String attributedBody;

    private String belong_number;

    private long date;

    private long dateRead;

    private String guid;

    private String handleId;

    private int hasDdResults;

    private boolean isDeleted;

    private String isFromMe;

    private String sendDate;

    private String sendStatus;

    private String service;

    private String text;
}
