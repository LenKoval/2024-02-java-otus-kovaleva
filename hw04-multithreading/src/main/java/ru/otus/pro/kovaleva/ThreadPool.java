package ru.otus.pro.kovaleva;


import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final ThreadWorker[] threadWorker;
    private boolean canWork = true;

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

        for (ThreadWorker thread : threadWorker) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new IllegalStateException("Trying to pass the job to a destroyed thread pool.");
            }
        }
    }

    public void execute(Runnable task) {
        if (!canWork) {
            throw new IllegalStateException("Cannot execute, thread pool has been shut down.");
        }

        synchronized (tasks) {
            tasks.offer(task);
            tasks.notify();
        }
    }

    private class ThreadWorker extends Thread {
        @Override
        public void run() {
            while (canWork) {
                Runnable task;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }
}
