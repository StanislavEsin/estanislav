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

    /**
     * demonstrationTwoThreadsWorkInParallel.
     */
    public void demonstrationWaitingOutputAndInterrupt() {
        String txt = "test  test test  ";

        System.out.println("Start first thread");

        Thread spacingCounter = new Thread(new SpacingCounter(txt));
        Thread wordCounter = new Thread(new WordCounter(txt));

        spacingCounter.start();
        wordCounter.start();

        try {
            spacingCounter.join(1000);

            if (spacingCounter.isAlive()) {
                spacingCounter.interrupt();
            }

            wordCounter.join(1000);

            if (wordCounter.isAlive()) {
                wordCounter.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End first thread");
    }

    /**
     * demonstrationTwoThreadsWorkInParallel.
     */
    public void demonstrationWhenTimePassesMainThreadStops() {
        String txt = "test  test test  test  test test  test  test test  test  test test  "
                + "test  test test  test  test test  test  test test  ";

        System.out.println("Start first thread");

        Thread wordCounter = new Thread(new WordCounter(txt));
        Thread time = new Thread(new Time(wordCounter, 0));
        time.isDaemon();

        time.start();
        wordCounter.start();

        System.out.println("End first thread");
    }

}