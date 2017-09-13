package ru.job4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class ConverterTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();

        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        int result = convert.next();

        assertThat(result, is(2));
    }

    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionThenShouldReturnContentValue() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();

        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        boolean result = convert.hasNext();

        assertThat(result, is(false));
    }

    /**
     * Test add.
     */
    @Test
    public void whenItHasInfinitelyManyElementsInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Arrays.asList(new Integer[]{1, 2, 3}).iterator(),
                Arrays.asList(new Integer[]{4, 5, 6, 7}).iterator()
        ).iterator();

        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        int result = convert.next();

        assertThat(result, is(7));
    }

    /**
     * Test add.
     */
    @Test
    public void whenCheckNextPositionInfinitelyManyElementsThenShouldReturnContentValue() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Arrays.asList(new Integer[]{1, 2, 3}).iterator(),
                Arrays.asList(new Integer[]{4, 5, 6, 7}).iterator()
        ).iterator();

        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        convert.next();
        boolean result = convert.hasNext();

        assertThat(result, is(false));
    }

    /**
     * Test add.
     * @throws ArrayIndexOutOfBoundsException - .
     */
    @Test
    public void whenItHasTwoInnerItThenArrayIndexOutOfBoundsException()
            throws ArrayIndexOutOfBoundsException {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();

        thrown.expect(ArrayIndexOutOfBoundsException.class);

        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        convert.next();
    }
}