package ru.job4j.wait_notify;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.10.2017
 */
public class SimpleThreadPoolTest {
    /**
     * Test.
     */
    @Test
    @Ignore("demonstration")
    public void demonstration() {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        for (int i = 0; i < 100; i++) {
            threadPool.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                }
            }));
        }

        threadPool.shutdownNow();
    }
}