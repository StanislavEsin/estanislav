package ru.job4j.units;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UnitUtilsTest {
    /**
     */
    private final Set<Unit> set = new TreeSet<>();

    /**
     */
    @Before
    public void setUp() {
        set.clear();
        set.add(new Unit("K1\\SK2"));
        set.add(new Unit("K1\\SK1"));
        set.add(new Unit("K1\\SK1\\SSK2"));
        set.add(new Unit("K2"));
        set.add(new Unit("K2\\SK1\\SSK1"));
        set.add(new Unit("K1\\SK1\\SSK1"));
        set.add(new Unit("K2\\SK1\\SSK2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListWithoutCodesOfUpperLevelUnitsThenListWithCodesOfUpperLevelUnits() {
        UnitUtils unitUtils = new UnitUtils();

        Set<Unit> result = unitUtils.addCodesOfUpperLevelUnits(set);

        Unit[] expected = {new Unit("K1"),
                new Unit("K1\\SK1"),
                new Unit("K1\\SK1\\SSK1"),
                new Unit("K1\\SK1\\SSK2"),
                new Unit("K1\\SK2"),
                new Unit("K2"),
                new Unit("K2\\SK1"),
                new Unit("K2\\SK1\\SSK1"),
                new Unit("K2\\SK1\\SSK2")};
        assertThat(result.toArray(), is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListWithoutCodesOfUpperLevelUnitsThenListWithCodesOfUpperLevelUnitsAndSortingDescending() {
        UnitUtils unitUtils = new UnitUtils();

        Set<Unit> result = unitUtils.sortingDescending(set);

        Unit[] expected = {new Unit("K2"),
                new Unit("K2\\SK1"),
                new Unit("K2\\SK1\\SSK2"),
                new Unit("K2\\SK1\\SSK1"),
                new Unit("K1"),
                new Unit("K1\\SK2"),
                new Unit("K1\\SK1"),
                new Unit("K1\\SK1\\SSK2"),
                new Unit("K1\\SK1\\SSK1")};
        assertThat(result.toArray(), is(expected));
    }
}