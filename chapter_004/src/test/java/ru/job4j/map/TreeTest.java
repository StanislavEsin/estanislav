package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 23.09.2017
 */
public class TreeTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 1);
        Iterator<Integer> iterator = tree.iterator();

        int result = iterator.next();

        assertThat(result, is(1));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        Tree<String> tree = new Tree<>();
        tree.add(null, "1");
        tree.add("1", "1-1");
        tree.add("1-1", "1-1-1");
        tree.add("1-1", "1-1-2");
        Iterator<String> iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();

        String result = iterator.next();

        assertThat(result, is("1-1-2"));
    }
}