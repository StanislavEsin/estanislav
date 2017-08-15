package ru.job4j;

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
public class UtilsTest {
    /**
     * Test add.
     */
    @Test
    public void whenSubStringInStringThenTrue() {
        Utils utils = new Utils();

        String string = "Привет";
        String subString = "иве";
        boolean result = utils.contains(string, subString);

        assertThat(result, is(true));
    }

    /**
     * Test add.
     */
    @Test
    public void whenTwoSortArrayThenMergeOne() {
        Utils utils = new Utils();

        int[] arrayA = {1, 2, 3, 5, 8};
        int[] arrayB = {1, 4, 7, 30, 55};
        int[] result = utils.merge(arrayA, arrayB);

        int[] expected = {1, 1, 2, 3, 4, 5, 7, 8, 30, 55};
        assertThat(result, is(expected));
    }
}