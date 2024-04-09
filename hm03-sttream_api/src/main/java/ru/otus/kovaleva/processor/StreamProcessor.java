package ru.otus.kovaleva.processor;

import ru.otus.kovaleva.task.StatusTask;
import ru.otus.kovaleva.task.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProcessor {
    private static List<Task> getListTasksBySelectedStatus(List<Task> tasks, StatusTask statusTask) {
        return tasks.stream()
                .filter(task -> task.getStatusTask() == statusTask)
                .collect(Collectors.toList());
    }

    private static boolean checkTaskWithSpecifiedID(List<Task> tasks, int id) {
        return tasks.stream()
                .anyMatch(task -> task.getId() == id);
    }

    private static List<Task> getListTasksSortedByStatus(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatusTask).reversed())
                .collect(Collectors.toList());
    }

    private static int countNumberTasksByCertainStatus(List<Task> tasks, StatusTask statusTask) {
        return (int) tasks.stream().filter(task -> task.getStatusTask() == statusTask).count();
    }

    public static void printMethodsResults(List<Task> tasks, int id1, int id2, StatusTask statusTask) {
        System.out.println("Check task with specified ID = " + id1 + ": " + checkTaskWithSpecifiedID(tasks, id1)
                + "\nCheck task with specified ID = " + id2 + ": " + checkTaskWithSpecifiedID(tasks, id2)
                + "\nGet the list of tasks by selected status: " + getListTasksBySelectedStatus(tasks, statusTask)
                + "\nCount tasks by a certain status: " + countNumberTasksByCertainStatus(tasks, statusTask)
                + "\nGet the list of tasks sorted by status: " + getListTasksSortedByStatus(tasks));
    }
}
