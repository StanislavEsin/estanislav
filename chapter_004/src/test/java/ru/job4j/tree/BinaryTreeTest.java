package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 24.09.2017
 */
public class BinaryTreeTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(1);
        Iterator<Integer> iterator = binaryTree.iterator();

        int result = iterator.next();

        assertThat(result, is(1));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.add("test");
        binaryTree.add("1");
        binaryTree.add("TRest");
        binaryTree.add("1-1");
        binaryTree.add("ss");
        binaryTree.add("pp");
        binaryTree.add("1-1-1");
        binaryTree.add("1-1-2");
        Iterator<String> iterator = binaryTree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        String result = iterator.next();

        assertThat(result, is("pp"));
    }
}