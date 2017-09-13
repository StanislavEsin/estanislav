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
 * @since 12.09.2017
 */
public class IteratorPrimeNumberTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenGetNextCallThenShouldPointerForward() {
        IteratorPrimeNumber it = new IteratorPrimeNumber(new int[]{3, 4, 5, 6, 7});

        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(5));
    }

    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionThenShouldReturnContentValue() {
        IteratorPrimeNumber it = new IteratorPrimeNumber(new int[]{3, 4, 5, 6, 7});

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
    public void whenGetNextCallThenArrayIndexOutOfBoundsException()
            throws ArrayIndexOutOfBoundsException {
        IteratorPrimeNumber it = new IteratorPrimeNumber(new int[]{3, 4, 5, 6, 7});

        thrown.expect(ArrayIndexOutOfBoundsException.class);

        it.next();
        it.next();
        it.next();
        it.next();
    }
}