package ru.job4j.wait_notify;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * SimpleThreadPool.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.10.2017
 */
public class SimpleThreadPool {
    /**
     */
    private final HashSet<Worker> workers = new HashSet<Worker>();
    /**
     */
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    /**
     * Constructor.
     */
    public SimpleThreadPool() {
        int numberThreads = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < numberThreads; i++) {
            Worker worker = new Worker();
            this.workers.add(worker);
            worker.start();
        }
    }

    /**
     * add.
     * @param work - Runnable.
     */
    public void add(Runnable work) {
        synchronized (workQueue) {
            try {
                workQueue.put(work);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            workQueue.notify();
        }
    }

    /**
     * shutdownNow.
     * @return List - List<Runnable>.
     */
    public List<Runnable> shutdownNow() {
        List<Runnable> result;

        synchronized (workQueue) {
            result = new ArrayList<>(this.workQueue);
            for (Thread worker : this.workers) {
                worker.interrupt();
            }
        }

        return result;
    }

    /**
     * Worker.
     */
    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (workQueue) {
                    while (workQueue.isEmpty()) {
                        try {
                            workQueue.wait();
                        } catch (InterruptedException e) {

                        }
                    }

                    workQueue.poll().run();
                }
            }
        }
    }
}