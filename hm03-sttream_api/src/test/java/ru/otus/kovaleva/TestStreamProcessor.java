package ru.otus.kovaleva;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.kovaleva.processor.StreamProcessor;
import ru.otus.kovaleva.task.StatusTask;
import ru.otus.kovaleva.task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestStreamProcessor {

    private static List<Task> tasks = new ArrayList<>();

    @BeforeAll
    public static void fillList() {
        tasks.add(new Task(2, "Task2", StatusTask.ACCEPTED));
        tasks.add(new Task(3, "Task3", StatusTask.IN_PROGRESS));
        tasks.add(new Task(5, "Task5", StatusTask.ACCEPTED));
        tasks.add(new Task(4, "Task4", StatusTask.COMPLETED));
        tasks.add(new Task(1, "Task1", StatusTask.IN_PROGRESS));
        tasks.add(new Task(7, "Task7", StatusTask.ACCEPTED));
        tasks.add(new Task(9, "Task9", StatusTask.COMPLETED));
        tasks.add(new Task(8, "Task8", StatusTask.IN_PROGRESS));
    }
    @Test
    public void testCheckTask() {
        assertTrue(StreamProcessor.checkTask(tasks, 3));
        assertFalse(StreamProcessor.checkTask(tasks, 0));
    }

    @Test
    public void testCheckTaskException() {
        Throwable throwable = assertThrows(Exception.class, () -> {
            StreamProcessor.checkTask(tasks, -1);
        });
        assertNotNull(throwable.getMessage());
    }

    @Test
    public void testGetListTasks() {
        List<Task> list = StreamProcessor.getListTasks(tasks, StatusTask.COMPLETED);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetListTasksException() {
        Throwable throwable = assertThrows(Exception.class, () -> {
            StreamProcessor.getListTasks(tasks, null);
        });
        assertNotNull(throwable.getMessage());
    }

    @Test
    public void testCountNumberTasks() {
        assertEquals(3, StreamProcessor.countNumberTasks(tasks, StatusTask.IN_PROGRESS));
    }

    @Test
    public void testCountNumberTasksException() {
        List<Task> list = new ArrayList<>();
        Throwable throwable = assertThrows(Exception.class, () -> {
            StreamProcessor.countNumberTasks(list, StatusTask.COMPLETED);
        });
        assertNotNull(throwable.getMessage());
    }

    @Test
    public void testGetListTasksSorted() {
        assertEquals(StatusTask.COMPLETED, StreamProcessor.getListTasksSorted(tasks).getFirst().getStatusTask());
    }
}
