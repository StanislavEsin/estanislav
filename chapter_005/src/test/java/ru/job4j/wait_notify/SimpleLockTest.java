package ru.job4j.wait_notify;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.10.2017
 */
public class SimpleLockTest {
    /**
     * Test.
     */
    @Test
    public void whenUseSimpleLockThenWorksCorrectly() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SimpleLock lock = new SimpleLock();
                lock.lock();
                for (int j = 1; j <= 5; j++) {
                    System.out.print(j);
                }
                lock.unlock();
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(out.toString(), is("123451234512345123451234512345123451234512345123451234512345123"
                + "4512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234"
                + "5123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345"
                + "1234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451"
                + "2345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512"
                + "3451234512345123451234512345123451234512345123451234512345123451234512345"));
    }
}