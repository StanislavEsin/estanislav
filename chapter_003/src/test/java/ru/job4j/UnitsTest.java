package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.ArrayList;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UnitsTest {
    /**
     */
    private final List<String> array = new ArrayList<>();

    /**
     */
    @Before
    public void setUp() {
        array.clear();
        array.add("K1\\SK2");
        array.add("K1\\SK1");
        array.add("K1\\SK1\\SSK2");
        array.add("K2");
        array.add("K2\\SK1\\SSK1");
        array.add("K1\\SK1\\SSK1");
        array.add("K2\\SK1\\SSK2");
    }

    /**
     * Test add.
     */
    @Test
    public void whenListWithoutCodesOfUpperLevelUnitsAndNotSortingThenListWithCodesOfUpperLevelUnitsAndSortingAscending() {
        Units units = new Units();

        List<String> result = units.sorting(array, SortingMethod.ASCENDING);

        List<String> expected = new ArrayList<>();
        expected.add("K1");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK2");
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K2\\SK1\\SSK2");
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListWithoutCodesOfUpperLevelUnitsAndNotSortingThenListWithCodesOfUpperLevelUnitsAndSortingDescending() {
        Units units = new Units();

        List<String> result = units.sorting(array, SortingMethod.DESCENDING);

        List<String> expected = new ArrayList<>();
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK2");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K1");
        expected.add("K1\\SK2");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK1\\SSK1");
        assertThat(result, is(expected));
    }
}