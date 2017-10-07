package ru.job4j.threads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WordCounter.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class WordCounter implements Runnable {
    /**
     */
    String text;

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
        Matcher matcher = Pattern.compile("([^ ]+)").matcher(this.text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        System.out.println(count);
    }
}