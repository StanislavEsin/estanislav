package ru.job4j.set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 17.09.2017
 */
public class SimpleSetTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("test1");


        String result = simpleSet.iterator().next();

        assertThat(result, is("test1"));
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenAddTwoSameValueThenSecondNotAdded() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("test1");
        simpleSet.add("test1");

        Iterator<String> iterator = simpleSet.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);

        iterator.next();
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(2);
        simpleSet.add(5);
        simpleSet.add(8);

        Iterator<Integer> iterator = simpleSet.iterator();
        iterator.next();
        iterator.next();
        int result = iterator.next();

        assertThat(result, is(8));
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenGetNextCallThenArrayIndexOutOfBoundsException() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(2);

        Iterator<Integer> iterator = simpleSet.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }
}