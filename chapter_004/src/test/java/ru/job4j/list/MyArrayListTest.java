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
 * @since 14.09.2017
 */
public class MyArrayListTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        MyArrayList<String> myArray = new MyArrayList<>();
        myArray.add("test1");
        myArray.add("test2");

        String result = myArray.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOverCapacityThenArrayGrow() {
        MyArrayList<String> myArray = new MyArrayList<>(2);
        myArray.add("test1");
        myArray.add("test2");
        myArray.add("test3");

        String result = myArray.get(2);

        assertThat(result, is("test3"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        MyArrayList<Integer> myArray = new MyArrayList<>(2);
        myArray.add(2);
        myArray.add(5);
        myArray.add(8);

        Iterator<Integer> iterator = myArray.iterator();
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
        MyArrayList<String> myArray = new MyArrayList<>();
        myArray.add("test1");

        thrown.expect(IndexOutOfBoundsException.class);

        myArray.get(1);
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenGetNextCallThenArrayIndexOutOfBoundsException() {
        MyArrayList<Integer> myArray = new MyArrayList<>();
        myArray.add(2);

        Iterator<Integer> iterator = myArray.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }
}