package ru.job4j.monitore;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ThreadSafeSimpleArrayList.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class ThreadSafeSimpleArrayList<E> implements Iterable<E> {
    /**
     */
    private volatile Object[] container;
    /**
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Constructor.
     */
    public ThreadSafeSimpleArrayList() {
        this.container = new Object[0];
    }

    /**
     * add.
     * @param value - E
     * @return return - boolean
     */
    public synchronized boolean add(E value) {
        this.lock.lock();
        try {
            int length = this.container.length;
            Object[] newContainer = Arrays.copyOf(this.container, length + 1);
            newContainer[length] = value;
            this.container = newContainer;
            return true;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * get.
     * @param index - int
     * @throws IndexOutOfBoundsException - IndexOutOfBoundsException.
     * @return return - E
     */
    public E get(int index) {
        return (E) this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new ThreadSafeSimpleArrayList.Itr(this.container);
    }

    /**
     * Itr.
     */
    private class Itr implements Iterator<E> {
        /**
         */
        private final Object[] snapshot;
        /**
         */
        private int cursor;

        /**
         * Constructor.
         *@param snapshot - Object[].
         */
        Itr(Object[] snapshot) {
            this.snapshot = snapshot;
        }

        @Override
        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) snapshot[cursor++];
        }
    }
}