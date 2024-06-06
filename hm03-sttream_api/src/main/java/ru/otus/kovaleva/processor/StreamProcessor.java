package ru.otus.kovaleva.processor;

import ru.otus.kovaleva.task.StatusTask;
import ru.otus.kovaleva.task.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProcessor {
    public static List<Task> getListTasks(List<Task> tasks, StatusTask statusTask) {
        if (checkList(tasks) || statusTask == null) {
            throw new IllegalStateException("List is empty or status task is null.");
        }
        return tasks.stream()
                .filter(task -> task.getStatusTask() == statusTask)
                .collect(Collectors.toList());
    }

    public static boolean checkTask(List<Task> tasks, int id) {
        if (checkList(tasks) || id < 0) {
            throw new IllegalStateException("List is empty or entered id cannot be less than zero.");
        }
        return tasks.stream()
                .anyMatch(task -> task.getId() == id);
    }

    public static List<Task> getListTasksSorted(List<Task> tasks) {
        if (checkList(tasks)) {
            throw new IllegalStateException("List is empty.");
        }
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatusTask).reversed())
                .collect(Collectors.toList());
    }

    public static int countNumberTasks(List<Task> tasks, StatusTask statusTask) {
        if (checkList(tasks) || statusTask == null) {
            throw new IllegalStateException("List is empty or status task is null.");
        }
        return (int) tasks.stream().filter(task -> task.getStatusTask() == statusTask).count();
    }

    private static boolean checkList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return true;
        }
        return false;
    }
}
