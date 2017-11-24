package ru.job4j.threads;

import org.junit.Ignore;
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
    @Ignore("demonstration")
    public void demonstrationTwoThreadsWorkInParallel() {
        DemonstrationParallelExecution demonstration = new DemonstrationParallelExecution();
        demonstration.demonstrationTwoThreadsWorkInParallel();
    }

    /**
     * Test.
     */
    @Test
    @Ignore("demonstration")
    public void demonstrationWaitingOutputAndInterrupt() {
        DemonstrationParallelExecution demonstration = new DemonstrationParallelExecution();
        demonstration.demonstrationWaitingOutputAndInterrupt();
    }

    /**
     * Test.
     */
    @Test
    @Ignore("demonstration")
    public void demonstrationWhenTimePassesMainThreadStops() {
        DemonstrationParallelExecution demonstration = new DemonstrationParallelExecution();
        demonstration.demonstrationWhenTimePassesMainThreadStops();
    }
}