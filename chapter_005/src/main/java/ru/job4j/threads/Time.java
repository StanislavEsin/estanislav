package ru.job4j.threads;

/**
 * Time.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 08.10.2017
 */
public class Time implements Runnable {
    private final long timeMillis;
    private final Thread stopThread;

    /**
     * Constructor.
     * @param timeMillis - long.
     */
    public Time(Thread thread,long timeMillis) {
        this.timeMillis = timeMillis;
        this.stopThread = thread;
    }

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(this.timeMillis);

            if (this.stopThread.isAlive()) {
                this.stopThread.interrupt();
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}