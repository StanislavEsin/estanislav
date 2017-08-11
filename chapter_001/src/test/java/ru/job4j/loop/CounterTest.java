package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {
    /**
     * Test add.
     */
    @Test
    public void whenRangeNumbersSetThenAmountEvenNumbers() {
        Counter counter = new Counter();

        int result = counter.add(3, 10);

        assertThat(result, is(28));
    }
}