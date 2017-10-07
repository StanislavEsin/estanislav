package ru.job4j.threads;

/**
 * DemonstrationParallelExecution.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 07.10.2017
 */
public class DemonstrationParallelExecution {
    /**
     * demonstrationTwoThreadsWorkInParallel.
     */
    public void demonstrationTwoThreadsWorkInParallel() {
        String txt = "test  test test  ";

        System.out.println("Start first thread");

        Thread spacingCounter = new Thread(new SpacingCounter(txt));
        Thread wordCounter = new Thread(new WordCounter(txt));

        spacingCounter.start();
        wordCounter.start();

        System.out.println("End first thread");
    }
}