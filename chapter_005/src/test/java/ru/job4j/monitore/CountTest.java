package ru.job4j.monitore;

import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 10.10.2017
 */
public class CountTest {
    /**
     * Test.
     */
    @Test
    public void demonstration() {
        Count count = new Count();
        Thread threadCountA = new Thread(count);
        Thread threadCountB = new Thread(count);
        Thread threadCountC = new Thread(count);
        Thread threadCountD = new Thread(count);
        Thread threadCountE = new Thread(count);

        threadCountA.start();
        threadCountB.start();
        threadCountC.start();
        threadCountD.start();
        threadCountE.start();
        try {
            threadCountA.join();
            threadCountB.join();
            threadCountC.join();
            threadCountD.join();
            threadCountE.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}