package ru.job4j.wait_notify;

/**
 * SimpleLock.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.10.2017
 */
public class SimpleLock {
    /**
     */
    private static boolean locked = false;
    /**
     */
    private static final Object lockObject = new Object();

    /**
     * lock.
     */
    public void lock() {
        synchronized (lockObject) {
            while (locked) {
                try {
                    lockObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            locked = true;
        }
    }

    /**
     * unlock.
     */
    public void unlock() {
        synchronized (lockObject) {
            locked = false;
            lockObject.notify();
        }
    }
}