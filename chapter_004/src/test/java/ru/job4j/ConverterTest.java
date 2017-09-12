package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class ConverterTest {
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
}