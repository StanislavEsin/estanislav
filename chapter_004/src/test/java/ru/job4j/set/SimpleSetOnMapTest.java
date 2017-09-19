package ru.job4j.set;

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
 * @since 19.09.2017
 */
public class SimpleSetOnMapTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        SimpleSetOnMap<String> simpleSetOnMap = new SimpleSetOnMap<>();
        simpleSetOnMap.add("test1");


        String result = simpleSetOnMap.iterator().next();

        assertThat(result, is("test1"));
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenAddTwoSameValueThenSecondNotAdded() {
        SimpleSetOnMap<String> simpleSetOnMap = new SimpleSetOnMap<>();
        simpleSetOnMap.add("test1");
        simpleSetOnMap.add("test1");

        Iterator<String> iterator = simpleSetOnMap.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);

        iterator.next();
    }

    /**
     * Test add.
     */
    @Test
    public void whenComparePerformanceOfTwoSolutionsThenOutTime() {
        SimpleSetOnMap<Integer> simpleSetOnMap = new SimpleSetOnMap<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            simpleSetOnMap.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Set on map: " + (end - start));

        SimpleSet<Integer> simpleSet = new SimpleSet<>();

        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            simpleSet.add(i);
        }
        end = System.currentTimeMillis();

        System.out.println("Set on array: " + (end - start));
    }
}