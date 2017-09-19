package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleSet.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     */
    private E[] container;
    /**
     */
    private int size;

    /**
     * Constructor.
     */
    public SimpleSet() {
        this.container = (E[]) new Object[10];
    }

    /**
     * add.
     * @param value - E
     * @return return - boolean
     */
    public boolean add(E value) {
        if (size != 0 && search(value) >= 0) {
            return false;
        }

        if (this.size + 1 == this.container.length) {
            int newCapacity = (int) (this.container.length * 1.75);
            this.container = Arrays.copyOf(this.container, newCapacity);
        }

        this.container[this.size++] = value;

        return true;
    }

    /**
     * search.
     * @param value - E
     * @return return - int
     */
    private int search(E value) {
        E[] copyContainer = (E[]) new Object[this.size];
        System.arraycopy(this.container, 0, copyContainer, 0, size);
        Arrays.sort(copyContainer);

        return Arrays.binarySearch(copyContainer, value);
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleSet.Itr();
    }

    /**
     * Itr.
     */
    private class Itr implements Iterator<E> {
        /**
         */
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor <= SimpleSet.this.size - 1;
        }

        @Override
        public E next() {
            if (cursor <= SimpleSet.this.size - 1) {
                return SimpleSet.this.container[cursor++];
            }

            throw new NoSuchElementException();
        }
    }
}