package ru.otus.pro.kovaleva;


import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final ThreadWorker[] threadWorker;
    private volatile boolean canWork = true;

    public ThreadPool(int threadsNumber) {
        if (threadsNumber <= 0) {
            throw new IllegalStateException("The number of threads in the pool must be greater than or equal to 1.");
        }

        threadWorker = new ThreadWorker[threadsNumber];
        for (int i = 0; i < threadWorker.length; i++) {
            threadWorker[i] = new ThreadWorker();
            threadWorker[i].start();
        }
    }

    public void shutdown() {
        canWork = false;
        for (ThreadWorker thread : threadWorker) {
            thread.interrupt();
        }
    }

    public void execute(Runnable task) {
        if (task == null || !canWork) {
            throw new IllegalStateException("Cannot execute, thread pool has been shut down or task is null.");
        }

        synchronized (tasks) {
            tasks.offer(task);
            tasks.notify();
        }
    }

    private class ThreadWorker extends Thread {
        private final Object lock = new Object();
        @Override
        public void run() {
            while (canWork) {
                Runnable task;
                synchronized (lock) {
                    while (tasks.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    throw new IllegalStateException("Thread pool interrupted " + e.getMessage());
                }
            }
        }
    }
}
