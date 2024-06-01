package ru.otus.pro.kovaleva;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(includeFieldNames = true)
@RepositoryTable(title = "users")
public class User {
    @RepositoryIdField
    @RepositoryField
    private Long id;

    @RepositoryField
    private String login;

    @RepositoryField
    private String password;

    @RepositoryField
    private String nickname;

    public User(String login, String password, String nickname) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Long id, String login, String password, String nickname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
}
