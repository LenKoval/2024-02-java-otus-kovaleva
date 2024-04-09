package ru.otus.kovaleva;

import ru.otus.kovaleva.processor.StreamProcessor;
import ru.otus.kovaleva.task.StatusTask;
import ru.otus.kovaleva.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(2, "Task2", StatusTask.ACCEPTED));
        taskList.add(new Task(3, "Task3", StatusTask.IN_PROGRESS));
        taskList.add(new Task(5, "Task5", StatusTask.ACCEPTED));
        taskList.add(new Task(4, "Task4", StatusTask.COMPLETED));
        taskList.add(new Task(1, "Task1", StatusTask.IN_PROGRESS));
        taskList.add(new Task(7, "Task7", StatusTask.ACCEPTED));
        taskList.add(new Task(9, "Task9", StatusTask.COMPLETED));
        taskList.add(new Task(8, "Task8", StatusTask.IN_PROGRESS));

        StreamProcessor.printMethodsResults(taskList, 3, 0, StatusTask.COMPLETED);
    }
}
