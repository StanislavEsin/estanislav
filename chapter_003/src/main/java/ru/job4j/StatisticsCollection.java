package ru.job4j;

import java.util.Comparator;

/**
 * StatisticsCollection.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StatisticsCollection {
    /**
     */
    private String name;
    /**
     */
    private long timeAdd;
    /**
     */
    private long timeDelete;

    /**
     * @see
     * @param name - String.
     * @param timeAdd - long.
     * @param timeDelete - long.
     */
    public StatisticsCollection(String name, long timeAdd, long timeDelete) {
        this.name = name;
        this.timeAdd = timeAdd;
        this.timeDelete = timeDelete;
    }

    /**
     * getName.
     * @return String.
     */
    public String getName() {
        return name;
    }

    /**
     * getTimeAdd.
     * @return long.
     */
    public long getTimeAdd() {
        return timeAdd;
    }

    /**
     * getTimeDelete.
     * @return long.
     */
    public long getTimeDelete() {
        return timeDelete;
    }

    /**
     * StatisticsCollectionComparatorTimeAdd.
     */
    public static class StatisticsCollectionComparatorTimeAdd
            implements Comparator<StatisticsCollection> {
        @Override
        public int compare(StatisticsCollection t1, StatisticsCollection t2) {
            return new Long(t1.getTimeAdd()).compareTo(new Long(t2.getTimeAdd()));
        }
    }

    /**
     * StatisticsCollectionComparatorTimeDelete.
     */
    public static class StatisticsCollectionComparatorTimeDelete
            implements Comparator<StatisticsCollection> {
        @Override
        public int compare(StatisticsCollection t1, StatisticsCollection t2) {
            return new Long(t1.getTimeDelete()).compareTo(new Long(t2.timeDelete));
        }
    }
}