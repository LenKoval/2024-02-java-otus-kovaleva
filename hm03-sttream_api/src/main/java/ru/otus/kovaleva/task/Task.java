package ru.otus.kovaleva.task;

public class Task {
    private int id;
    private String taskName;
    private StatusTask statusTask;

    public Task(int id, String taskName, StatusTask statusTask) {
        this.id = id;
        this.taskName = taskName;
        this.statusTask = statusTask;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", statusTask=" + statusTask +
                '}';
    }
}
