package ru.job4j.list;

import java.util.HashSet;
import java.util.Set;

/**
 * Utils.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 17.09.2017
 */
public class Utils {
    /**
     * hasCycle.
     * @param first - Node
     * @return return - boolean
     */
    boolean hasCycle(Node first) {
        Set<Node> set = new HashSet<>();

        Node tmpNode = first;
        while (tmpNode != null) {
            if (set.add(tmpNode)) {
                tmpNode = tmpNode.next;
            } else {
                return true;
            }
        }

        return false;
    }
}