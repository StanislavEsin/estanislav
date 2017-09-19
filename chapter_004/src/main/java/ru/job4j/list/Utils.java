package ru.job4j.list;

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
        Node externalNode = first;
        while (externalNode != null) {

            Node inerNode = externalNode.next;
            while (inerNode != null) {
                if (externalNode.equals(inerNode)) {
                    return true;
                }

                inerNode = inerNode.next;
            }

            externalNode = externalNode.next;
        }

        return false;
    }
}