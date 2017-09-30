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
    private Node<T, V>[] nodes;

    /**
     * Constructor.
     */
    public ReferenceBook() {
        this.nodes = new Node[16];
    }

    /**
     * Node.
     * @param <T>
     * @param <V>
     */
    private class Node<T, V> {
        /**
         */
        final T key;
        /**
         */
        V value;

        /**
         * Constructor.
         * @param key - T
         * @param value - V
         */
        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
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

        if (this.nodes[index] != null) {
            return false;
        }

        this.nodes[index] = new Node(key, value);

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

        if (this.nodes[index] != null && key.equals(this.nodes[index].key)) {
            return this.nodes[index].value;
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

        if (this.nodes[index] != null && key.equals(this.nodes[index].key)) {
            this.nodes[index] = null;

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
        return (key == null) ? 0 : key.hashCode() & (this.nodes.length - 1);
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
            for (int i = cursor; i < ReferenceBook.this.nodes.length; i++) {
                if (ReferenceBook.this.nodes[i] != null) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public T next() {
            for (int i = cursor; i < ReferenceBook.this.nodes.length; i++) {
                if (ReferenceBook.this.nodes[i] != null) {
                    this.cursor = i + 1;
                    return ReferenceBook.this.nodes[i].key;
                }
            }

            throw new NoSuchElementException();
        }
    }
}