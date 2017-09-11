package ru.job4j;

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
public class IteratorTwoDimensionalArrayTest {
    /**
     * Test add.
     */
    @Test
    public void whenGetNextCallThenShouldPointerForward() {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 2}, {3, 4}});

        it.next();
        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(3));
    }

    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionThenShouldReturnContantValue() {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 2}, {3, 4}});

        it.next();
        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}