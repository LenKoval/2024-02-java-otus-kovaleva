package ru.otus.pro.kovaleva.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {

    @JsonProperty("ROWID")
    private long rowId;

    @JsonProperty("attributedBody")
    private String attributedBody;

    @JsonProperty("belong_number")
    private String belongNumber;

    @JsonProperty("date")
    private long date;

    @JsonProperty("date_read")
    private long dateRead;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("handle_id")
    private String handleId;

    @JsonProperty("has_dd_results")
    private int hasDdResults;

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    @JsonProperty("is_from_me")
    private String isFromMe;

    @JsonProperty("send_time")
    private String sendDate;

    @JsonProperty("send_status")
    private String sendStatus;

    @JsonProperty("service")
    private String service;

    @JsonProperty("text")
    private String text;
}
