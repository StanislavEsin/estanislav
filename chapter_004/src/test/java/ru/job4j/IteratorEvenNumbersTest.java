package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class IteratorEvenNumbersTest {
    /**
     * Test add.
     */
    @Test
    public void whenGetNextCallThenShouldPointerForward() {
        IteratorEvenNumbers it = new IteratorEvenNumbers(new int[]{4, 2, 1, 1});

        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(2));
    }

    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionThenShouldReturnContentValue() {
        IteratorEvenNumbers it = new IteratorEvenNumbers(new int[]{4, 2, 1, 1});

        it.next();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}