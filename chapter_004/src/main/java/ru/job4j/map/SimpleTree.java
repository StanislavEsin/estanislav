package ru.job4j.map;

/**
 * SimpleTree.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * add.
     * @param parent parent.
     * @param child child.
     * @return - boolean
     */
    boolean add(E parent, E child);
}