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
 * @since 18.09.2017
 */
public class SimpleSetOnLinkedListTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddTwoSameValueThenSecondNotAdded() {
        SimpleSetOnLinkedList<String> simpleSetOnLinkedList = new SimpleSetOnLinkedList<>();
        simpleSetOnLinkedList.add("test1");
        simpleSetOnLinkedList.add("test1");

        int result = simpleSetOnLinkedList.size();

        assertThat(result, is(1));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        SimpleSetOnLinkedList<Integer> simpleSetOnLinkedList = new SimpleSetOnLinkedList<>();
        simpleSetOnLinkedList.add(2);
        simpleSetOnLinkedList.add(5);
        simpleSetOnLinkedList.add(8);

        Iterator<Integer> iterator = simpleSetOnLinkedList.iterator();
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
        SimpleSetOnLinkedList<Integer> simpleSetOnLinkedList = new SimpleSetOnLinkedList<>();
        simpleSetOnLinkedList.add(2);

        Iterator<Integer> iterator = simpleSetOnLinkedList.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }
}