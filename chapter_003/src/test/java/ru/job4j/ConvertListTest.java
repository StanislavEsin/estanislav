package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertListTest {
    /**
     * Test add.
     */
    @Test
    public void whenTwoDimensionalArrayThenList() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ConvertList convertList = new ConvertList();

        List<Integer> result = convertList.toList(array);

        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            expected.add(i);
        }
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListThenTwoDimensionalArray() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            list.add(i);
        }
        ConvertList convertList = new ConvertList();

        int[][] result = convertList.toArray(list, 3);

        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenInListArrayThenListValue() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        ConvertList convertList = new ConvertList();

        List<Integer> result = convertList.convert(list);

        List<Integer> expected = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6});
        assertThat(result, is(expected));
    }
}