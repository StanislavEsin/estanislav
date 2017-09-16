package ru.job4j.list;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.EmptyStackException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 16.09.2017
 */
public class SimpleQueueTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.push("test1");
        simpleQueue.push("test2");

        String result = simpleQueue.poll();

        assertThat(result, is("test1"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenPollThenShouldReturnTheSameValueAndWillLeaveStack() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.push("test1");
        simpleQueue.push("test2");

        simpleQueue.poll();
        int result = simpleQueue.size();

        assertThat(result, is(1));
    }

    /**
     * Test add.
     * @throws EmptyStackException - .
     */
    @Test
    public void whenPollFromEmptyStackThenEmptyStackException() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();

        thrown.expect(EmptyStackException.class);

        simpleQueue.poll();
    }
}