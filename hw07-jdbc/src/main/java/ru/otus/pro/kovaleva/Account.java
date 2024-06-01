package ru.otus.pro.kovaleva;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(includeFieldNames = true)
@RepositoryTable(title = "accounts")
public class Account {

    @RepositoryIdField
    @RepositoryField(name = "id")
    private Long id;

    @RepositoryField(name = "amount")
    private Long amount;

    @RepositoryField(name = "account_type")
    private String accountType;

    @RepositoryField(name = "status")
    private String status;

    public Account(Long amount, String accountType, String status) {
        this.amount = amount;
        this.accountType = accountType;
        this.status = status;
    }
}
