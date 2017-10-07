package ru.job4j.threads;

/**
 * WordCounter.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class WordCounter implements Runnable {
    /**
     */
    private final String text;

    /**
     * Constructor.
     * @param text - String.
     */
    public WordCounter(String text) {
        this.text = text;
    }

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        char[] array = this.text.toCharArray();

        int tempCount = 0;
        int countWord = 0;
        for (int i = 0; i < array.length; i++) {
            if (currentThread.isInterrupted()) {
                return;
            }

            if (' ' == array[i]) {
                if (tempCount != 0) {
                    countWord++;
                    tempCount = 0;
                }
            } else {
                tempCount++;
            }
        }

        System.out.println(countWord);
    }
}