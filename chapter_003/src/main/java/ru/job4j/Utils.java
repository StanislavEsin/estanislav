package ru.job4j;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Utils.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Utils {
    /**
     * performanceCollections.
     * @param list - List<Collection>.
     */
    public void printPerformanceCollections(List<Collection> list) {
        List<StatisticsCollection> arrayStatColl = new ArrayList<>();

        for (Collection valList : list) {
            long timeAdd = this.add(valList, 100_000);
            long timeDelete = this.delete(valList, 1_000);
            arrayStatColl.add(new StatisticsCollection(valList.getClass().getSimpleName(), timeAdd, timeDelete));
        }

        System.out.println("Статистика по добавлению:");
        System.out.println("----------------------------------");

        Collections.sort(arrayStatColl, new StatisticsCollection.StatisticsCollectionComparatorTimeAdd());

        for (int i = 0; i < arrayStatColl.size(); i++) {
            StatisticsCollection value = arrayStatColl.get(i);
            System.out.println(String.format("%s. %s - %s миллисекунд.",
                    i + 1, value.getName(), value.getTimeAdd()));
        }

        System.out.println("----------------------------------");
        System.out.println("Статистика по удалению:");
        System.out.println("----------------------------------");

        Collections.sort(arrayStatColl, new StatisticsCollection.StatisticsCollectionComparatorTimeDelete());

        for (int i = 0; i < arrayStatColl.size(); i++) {
            StatisticsCollection value = arrayStatColl.get(i);
            System.out.println(String.format("%s. %s - %s миллисекунд.",
                    i + 1, value.getName(), value.getTimeDelete()));
        }
    }

    /**
     * add.
     * @param collection - Collection<String>.
     * @param amount - int.
     * @return long.
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.format("a_%s", i));
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    /**
     * delete.
     * @param collection - Collection<String>.
     * @param amount - int.
     * @return long.
     */
    public long delete(Collection<String> collection, int amount) {
        long start, end;

        if (!(collection instanceof Set)) {
            start = System.currentTimeMillis();
            for (int i = 0; i < amount; i++) {
                collection.remove(i);
            }
            end = System.currentTimeMillis();
        } else {
            start = System.currentTimeMillis();
            for (int i = 0; i < amount; i++) {
                collection.remove(String.format("a_%s", i));
            }
            end = System.currentTimeMillis();
        }

        return end - start;
    }
}