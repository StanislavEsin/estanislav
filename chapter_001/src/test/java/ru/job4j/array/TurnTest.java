package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TurnTest {
    /**
     * Test add.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();

        int[] array = {2, 6, 1, 4};
        int[] result = turn.back(array);

        int[] expectedArray = {4, 1, 6, 2};
        assertThat(result, is(expectedArray));
    }

    /**
     * Test add.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();

        int[] array = {1, 2, 3, 4, 5};
        int[] result = turn.back(array);

        int[] expectedArray = {5, 4, 3, 2, 1};
        assertThat(result, is(expectedArray));
    }
}