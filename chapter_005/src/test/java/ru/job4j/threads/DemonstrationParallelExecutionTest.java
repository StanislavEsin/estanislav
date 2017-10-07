package ru.job4j.threads;

import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 07.10.2017
 */
public class DemonstrationParallelExecutionTest {
    /**
     * Test.
     */
    @Test
    public void demonstrationTwoThreadsWorkInParallel() {
        DemonstrationParallelExecution demonstration = new DemonstrationParallelExecution();
        demonstration.demonstrationTwoThreadsWorkInParallel();
    }
}