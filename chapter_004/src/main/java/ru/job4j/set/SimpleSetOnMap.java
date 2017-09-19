package ru.job4j.set;

import java.util.HashMap;
import java.util.Iterator;

/**
 * SimpleSetOnMap.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class SimpleSetOnMap<E> implements Iterable {
    /**
     */
    private HashMap<E, Object> map;
    /**
     */
    private final Object present = new Object();

    /**
     * Constructor.
     */
    public SimpleSetOnMap() {
        this.map = new HashMap<>();
    }

    /**
     * add.
     * @param value - E
     * @return return - boolean
     */
    public boolean add(E value) {
        return this.map.put(value, present) == null;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }
}