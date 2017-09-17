package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 17.09.2017
 */
public class UtilsTest {
    /**
     * Test add.
     */
    @Test
    public void whenListContainsClosuresThenReturnTrue() {
        Node first = new Node<Integer>(1);
        Node two = new Node<Integer>(2);
        Node third = new Node<Integer>(3);
        Node four = new Node<Integer>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean result = new Utils().hasCycle(first);

        assertThat(result, is(true));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListDoesNotContainClosuresThenReturnFalse() {
        Node first = new Node<Integer>(1);
        Node two = new Node<Integer>(2);
        Node third = new Node<Integer>(3);
        Node four = new Node<Integer>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        boolean result = new Utils().hasCycle(first);

        assertThat(result, is(false));
    }
}