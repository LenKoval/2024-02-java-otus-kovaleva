package ru.otus.pro.kovaleva.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

    private String first;

    @JsonProperty("handle_id")
    private long handleId;

    @JsonProperty("image_path")
    private String imagePath;

    private String last;

    private String middle;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String service;

    @JsonProperty("thumb_path")
    private String thumbPath;
}
