package ru.job4j.threads;

import org.junit.Test;

/**
 * DemonstrationParallelExecution.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class DemonstrationParallelExecution {
    @Test
    public void demonstration() {
        String txt = "test  test test  ";

        System.out.println("Start first thread");
        Thread spacingCounter = new Thread(new SpacingCounter(txt));
        Thread wordCounter = new Thread(new WordCounter(txt));
        spacingCounter.start();
        wordCounter.start();
        System.out.println("End first thread");
    }
}