package ru.job4j.threads;

/**
 * SpacingCounter.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class SpacingCounter implements Runnable {
    /**
     */
    private final String text;

    /**
     * Constructor.
     * @param text - String.
     */
    public SpacingCounter(String text) {
        this.text = text;
    }

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        char[] array = this.text.toCharArray();

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (currentThread.isInterrupted()) {
                return;
            }

            if (' ' == array[i]) {
                count++;
            }
        }

        System.out.println(count);
    }
}