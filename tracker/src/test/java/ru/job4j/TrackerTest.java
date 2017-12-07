package ru.job4j;

import org.junit.Test;
import ru.job4j.dao.ram.Tracker;
import ru.job4j.domain.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class TrackerTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);

        Item result = tracker.getAll().get(0);

        Item expected = item;
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenGetFullListItemsThenFullListItemsWithoutNull() {
        Tracker tracker = new Tracker();
        Item itemA = new Item("test1", "testDescription", 123L);
        Item itemB = new Item("test2", "testDescription_2", 123L);
        tracker.add(itemA);
        tracker.add(itemB);

        List<Item> result = tracker.getAll();

        List<Item> expected = new ArrayList<>(Arrays.asList(new Item[]{itemA, itemB}));
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenGetItemForIdThenGetItemForId() {
        Tracker tracker = new Tracker();
        Item itemA = new Item("5", "test1", "testDescription", 123L);
        Item itemB = new Item("test2", "testDescription_2", 123L);
        tracker.add(itemA);
        tracker.add(itemB);

        Item result = tracker.findById("5");

        Item expected = itemA;
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenGetItemsForNameThenGetArrayItemsForName() {
        Tracker tracker = new Tracker();
        Item itemA = new Item("test1", "testDescription", 123L);
        Item itemB = new Item("test2", "testDescription_2", 123L);
        Item itemC = new Item("test1", "testDescription_2", 123L);
        tracker.add(itemA);
        tracker.add(itemB);
        tracker.add(itemC);

        List<Item> result = tracker.findByName("test1");

        List<Item> expected = new ArrayList<>(Arrays.asList(new Item[]{itemA, itemC}));
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteItemFromTrackerThenTrackerWithoutSameItem() {
        Tracker tracker = new Tracker();
        Item itemA = new Item("test1", "testDescription", 123L);
        Item itemB = new Item("test2", "testDescription_2", 123L);
        Item itemC = new Item("test3", "testDescription_2", 123L);
        tracker.add(itemA);
        tracker.add(itemB);
        tracker.add(itemC);

        tracker.delete(itemB);
        List<Item> result = tracker.getAll();

        List<Item> expected = new ArrayList<>(Arrays.asList(new Item[]{itemA, itemC}));
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenChangeInStatusItemThenUpdateInTracker() {
        Tracker tracker = new Tracker();
        Item itemA = new Item("10", "test1", "testDescription", 123L);
        Item itemC = new Item("test2", "testDescription_2", 123L);
        tracker.add(itemA);
        tracker.add(itemC);

        Item itemB = new Item("10", "test3", "testDescription_3", 122L);
        tracker.update(itemB);
        Item tmp = tracker.findById("10");
        boolean result = tmp.getName().equals(itemB.getName())
                && tmp.getDesc().equals(itemB.getDesc())
                && tmp.getCreated() == itemB.getCreated()
                && Arrays.equals(tmp.getComments().toArray(), itemB.getComments().toArray());

        assertThat(result, is(true));
    }
}