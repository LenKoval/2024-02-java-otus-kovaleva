package ru.otus.kovaleva.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Task {
    private int id;
    private String taskName;
    private StatusTask statusTask;
}
