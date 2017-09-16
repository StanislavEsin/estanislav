package ru.job4j.list;

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
 * @since 16.09.2017
 */
public class MyLinkedListTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");
        myLinkedList.add("test2");

        String result = myLinkedList.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(2);
        myLinkedList.add(5);
        myLinkedList.add(8);

        Iterator<Integer> iterator = myLinkedList.iterator();
        iterator.next();
        iterator.next();
        int result = iterator.next();

        assertThat(result, is(8));
    }

    /**
     * Test add.
     * @throws IndexOutOfBoundsException - .
     */
    @Test
    public void whenGetIndexOutOfBoundsThenIndexOutOfBoundsException() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");

        thrown.expect(IndexOutOfBoundsException.class);

        myLinkedList.get(1);
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenGetNextCallThenArrayIndexOutOfBoundsException() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(2);

        Iterator<Integer> iterator = myLinkedList.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveFirstElementThenSecondElementBecomesTheFirst() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");
        myLinkedList.add("test2");
        myLinkedList.add("test3");

        myLinkedList.remove("test1");
        String result = myLinkedList.get(0);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveLastElementThenPenultimateElementBecomesTheLast() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");
        myLinkedList.add("test2");
        myLinkedList.add("test3");

        myLinkedList.remove("test3");
        String result = myLinkedList.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveElementInTheMiddleThenLinksAreRedefined() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");
        myLinkedList.add("test2");
        myLinkedList.add("test3");

        myLinkedList.remove("test2");
        String result = myLinkedList.get(1);

        assertThat(result, is("test3"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveElementWhichIsNotThenReturnFalse() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("test1");
        myLinkedList.add("test2");
        myLinkedList.add("test3");

        boolean result = myLinkedList.remove("test4");

        assertThat(result, is(false));
    }

}