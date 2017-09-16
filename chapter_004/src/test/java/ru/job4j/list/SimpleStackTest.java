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
public class SimpleStackTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("test1");
        simpleStack.push("test2");

        String result = simpleStack.poll();

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenPollThenShouldReturnTheSameValueAndWillLeaveStack() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("test1");
        simpleStack.push("test2");

        simpleStack.poll();
        int result = simpleStack.size();

        assertThat(result, is(1));
    }

    /**
     * Test add.
     * @throws EmptyStackException - .
     */
    @Test
    public void whenPollFromEmptyStackThenEmptyStackException() {
        SimpleStack<String> simpleStack = new SimpleStack<>();

        thrown.expect(EmptyStackException.class);

        simpleStack.poll();
    }
}