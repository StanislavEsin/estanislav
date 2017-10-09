package ru.job4j.jmm;

import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 09.10.2017
 */
public class ActionTest {
    /**
     * Test.
     * */
    @Test
    public void demonstrationThreadProblems() {
        Thread threadActionA = new Thread(new Action());
        Thread threadActionB = new Thread(new Action());

        threadActionA.start();
        threadActionB.start();
    }
}