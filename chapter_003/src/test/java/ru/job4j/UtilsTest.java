package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.List;

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
    public void testPerformanceCollections() {
        List<Collection> collectionList = new ArrayList<>();
        collectionList.add(new ArrayList<String>());
        collectionList.add(new LinkedList<String>());
        collectionList.add(new TreeSet<String>());

        Utils utils = new Utils();
        utils.printPerformanceCollections(collectionList);
    }
}