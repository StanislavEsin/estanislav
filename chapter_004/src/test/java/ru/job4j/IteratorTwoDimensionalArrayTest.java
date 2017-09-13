package ru.job4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void whenCheckNextPositionThenShouldReturnContentValue() {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 2}, {3, 4}});

        it.next();
        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }


    /**
     * Test add.
     */
    @Test
    public void whenGetNextCallNotSquareArrayThenShouldPointerForward() {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 1}, {2}});

        it.next();
        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(2));
    }


    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionNotSquareArrayThenShouldReturnContentValue() {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 1}, {2}});

        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }

    /**
     * Test add.
     * @throws ArrayIndexOutOfBoundsException - .
     */
    @Test
    public void whenGetNextCallNotSquareArrayThenArrayIndexOutOfBoundsException()
            throws ArrayIndexOutOfBoundsException {
        IteratorTwoDimensionalArray it = new IteratorTwoDimensionalArray(new int[][] {{1, 1}, {2}});

        thrown.expect(ArrayIndexOutOfBoundsException.class);

        it.next();
        it.next();
        it.next();
        it.next();
    }
}