package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 13.09.2017
 */
public class SimpleArrayTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);

        simpleArray.add(5);
        simpleArray.add(6);
        simpleArray.add(7);
        int result = simpleArray.get(1);

        assertThat(result, is(6));
    }

    /**
     * Test add.
     */
    @Test
    public void whenUpdateValueThenShouldReturnTheSameValue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);

        simpleArray.add(5);
        simpleArray.add(6);
        simpleArray.add(7);
        simpleArray.update(1, 10);
        int result = simpleArray.get(1);

        assertThat(result, is(10));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteValueOnThePositionThenShouldReturnNull() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);

        simpleArray.add(5);
        simpleArray.add(6);
        simpleArray.add(7);
        simpleArray.delete(1);
        int result = simpleArray.get(1);

        assertThat(result, is(7));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteValueOnTheValueThenShouldReturnNull() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);

        simpleArray.add(5);
        simpleArray.add(6);
        simpleArray.add(7);
        simpleArray.delete(new Integer(6));
        int result = simpleArray.get(1);

        assertThat(result, is(7));
    }
}