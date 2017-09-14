package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyArrayList.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class MyArrayList<E> implements Iterable<E> {
    /**
     */
    private E[] container;
    /**
     */
    private int size;
    /**
     */
    private static final int MAX_ARRAY_SIZE = 2147483639;

    /**
     * Constructor.
     */
    public MyArrayList() {
        this.container = (E[]) new Object[10];
    }

    /**
     * Constructor.
     * @param capacity - int
     */
    public MyArrayList(int capacity) {
        this.container = (E[]) new Object[capacity];
    }

    /**
     * add.
     * @param value - E
     * @return return - boolean
     */
    public boolean add(E value) {
        if (this.size + 1 == this.container.length) {
            int newCapacity = (int) (this.container.length * 1.75);

            if (newCapacity > MAX_ARRAY_SIZE) {
                throw new OutOfMemoryError();
            }

            this.container = Arrays.copyOf(this.container, newCapacity);
        }

        this.container[this.size++] = value;

        return true;
    }

    /**
     * get.
     * @param index - int
     * @return return - E
     */
    public E get(int index) {
        if (index - this.size >= 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayList.Itr();
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
            return cursor <= MyArrayList.this.size - 1;
        }

        @Override
        public E next() {
            if (cursor <= MyArrayList.this.size - 1) {
                return MyArrayList.this.container[cursor++];
            }

            throw new NoSuchElementException();
        }
    }
}