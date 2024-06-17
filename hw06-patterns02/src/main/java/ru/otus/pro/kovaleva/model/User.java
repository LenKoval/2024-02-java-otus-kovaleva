package ru.otus.pro.kovaleva.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String login;
    private String password;
    private String username;
}
