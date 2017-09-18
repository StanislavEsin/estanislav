package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyLinkedList.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class SimpleSetOnLinkedList<E> implements Iterable<E> {
    /**
     */
    private int size = 0;
    /**
     */
    private SimpleSetOnLinkedList.Node<E> first;
    /**
     */
    private SimpleSetOnLinkedList.Node<E> last;

    /**
     * add.
     * @param item - E
     * @return return - boolean
     */
    public boolean add(E item) {
        if (search(item) >= 0) {
            return false;
        }

        SimpleSetOnLinkedList.Node last = this.last;
        SimpleSetOnLinkedList.Node newNode = new SimpleSetOnLinkedList.Node(last, item, null);
        this.last = newNode;
        if (last == null) {
            this.first = newNode;
        } else {
            last.next = newNode;
        }

        ++this.size;
        return true;
    }

    /**
     * search.
     * @param value - E
     * @return return - int
     */
    private int search(E value) {
        int nextIndex = 0;
        SimpleSetOnLinkedList.Node nextNode = this.first;

        while (nextNode != null) {
            if (nextNode.item == null && value == null || nextNode.item.equals(value)) {
                return nextIndex;
            }

            nextNode = nextNode.next;
            ++nextIndex;
        }

        return -1;
    }

    /**
     * size.
     * @return return - int
     */
    public int size() {
        return this.size;
    }

    /**
     * node - определяем с какой стороны выгоднее итерироваться.
     * @param index - int
     * @return return - MyLinkedList.Node<E>
     */
    SimpleSetOnLinkedList.Node<E> node(int index) {
        SimpleSetOnLinkedList.Node var2;
        int var3;
        if (index < this.size >> 1) {
            var2 = this.first;

            for (var3 = 0; var3 < index; ++var3) {
                var2 = var2.next;
            }

            return var2;
        } else {
            var2 = this.last;

            for (var3 = this.size - 1; var3 > index; --var3) {
                var2 = var2.prev;
            }

            return var2;
        }
    }

    /**
     * Node.
     * @param <E>
     */
    private static class Node<E> {
        /**
         */
        private E item;
        /**
         */
        private SimpleSetOnLinkedList.Node<E> next;
        /**
         */
        private SimpleSetOnLinkedList.Node<E> prev;

        /**
         * Constructor.
         * @param prev - MyLinkedList.Node<E>
         * @param item - E
         * @param next - MyLinkedList.Node<E>
         */
        private Node(SimpleSetOnLinkedList.Node<E> prev, E item, SimpleSetOnLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleSetOnLinkedList.Itr();
    }

    /**
     * Itr.
     */
    private class Itr implements Iterator<E> {
        /**
         */
        private SimpleSetOnLinkedList.Node cursor;
        /**
         */
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return this.nextIndex < SimpleSetOnLinkedList.this.size;
        }

        @Override
        public E next() {
            if (this.nextIndex >= SimpleSetOnLinkedList.this.size) {
                throw new NoSuchElementException();
            }

            this.cursor = this.cursor == null ? SimpleSetOnLinkedList.this.first : this.cursor.next;
            ++nextIndex;

            return (E) cursor.item;
        }
    }
}