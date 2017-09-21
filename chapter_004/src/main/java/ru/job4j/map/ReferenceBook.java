package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ReferenceBook.
 * @param <T>
 * @param <V>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 20.09.2017
 */
public class ReferenceBook<T, V> implements Iterable<T> {
    /**
     */
    private T[] containerKey;
    /**
     */
    private V[] containerValue;

    /**
     * Constructor.
     */
    public ReferenceBook() {
        this.containerKey = (T[]) new Object[16];
        this.containerValue = (V[]) new Object[16];
    }

    /**
     * insert.
     * @param key - T
     * @param value - V
     * @return return - boolean
     */
    public boolean insert(T key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = this.hash(key);

        if (this.containerKey[index] != null) {
            return false;
        }

        this.containerKey[index] = key;
        this.containerValue[index] = value;

        return true;
    }

    /**
     * get.
     * @param key - T
     * @return return - V
     */
    public V get(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = this.hash(key);

        if (key.equals(this.containerKey[index])) {
            return this.containerValue[index];
        }

        return null;
    }

    /**
     * delete.
     * @param key - T
     * @return return - boolean
     */
    public boolean delete(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = this.hash(key);

        if (key.equals(this.containerKey[index])) {
            this.containerKey[index] = null;
            this.containerValue[index] = null;

            return true;
        }

        return false;
    }

    /**
     * hash.
     * @param key - T
     * @return return - int
     */
    private int hash(T key) {
        return (key == null) ? 0 : key.hashCode() & 15;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ReferenceBook.Itr();
    }

    /**
     * Itr.
     */
    private class Itr implements Iterator<T> {
        /**
         */
        private int cursor;

        @Override
        public boolean hasNext() {
            for (int i = cursor; i < ReferenceBook.this.containerKey.length; i++) {
                if (ReferenceBook.this.containerKey[i] != null) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public T next() {
            for (int i = cursor; i < ReferenceBook.this.containerKey.length; i++) {
                if (ReferenceBook.this.containerKey[i] != null) {
                    this.cursor = i + 1;
                    return ReferenceBook.this.containerKey[i];
                }
            }

            throw new NoSuchElementException();
        }
    }
}