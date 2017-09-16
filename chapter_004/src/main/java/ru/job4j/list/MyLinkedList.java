package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyLinkedList.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class MyLinkedList<E> implements Iterable<E> {
    /**
     */
    private int size = 0;
    /**
     */
    private MyLinkedList.Node<E> first;
    /**
     */
    private MyLinkedList.Node<E> last;

    /**
     * add.
     * @param item - E
     * @return return - boolean
     */
    public boolean add(E item) {
        MyLinkedList.Node last = this.last;
        MyLinkedList.Node newNode = new MyLinkedList.Node(last, item, null);
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
     * get.
     * @param index - int
     * @return return - E
     */
    public E get(int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return this.node(index).item;
    }

    /**
     * remove.
     * @param item - E
     * @return return - boolean
     */
    public boolean remove(E item) {
        boolean result = false;

        MyLinkedList.Node node;
        if (item == null) {
            for (node = this.first; node != null; node = node.next) {
                if (node.item == null) {
                    this.unlink(node);
                    result = true;
                    break;
                }
            }
        } else {
            for (node = this.first; node != null; node = node.next) {
                if (item.equals(node.item)) {
                    this.unlink(node);
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * unlink.
     * @param node - MyLinkedList.Node<E>
     */
    private void unlink(MyLinkedList.Node<E> node) {
        MyLinkedList.Node<E> tmpNodePrev = node.prev;
        MyLinkedList.Node<E> tmpNodeNext = node.next;

        if (tmpNodePrev == null) {
            this.first = tmpNodeNext;
        } else {
            tmpNodePrev.next = tmpNodeNext;
            node.prev = null;
        }

        if (tmpNodeNext == null) {
            this.last = tmpNodePrev;
        } else {
            tmpNodeNext.prev = tmpNodePrev;
            node.next = null;
        }

        --this.size;
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
    MyLinkedList.Node<E> node(int index) {
        MyLinkedList.Node var2;
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
        private MyLinkedList.Node<E> next;
        /**
         */
        private MyLinkedList.Node<E> prev;

        /**
         * Constructor.
         * @param prev - MyLinkedList.Node<E>
         * @param item - E
         * @param next - MyLinkedList.Node<E>
         */
        private Node(MyLinkedList.Node<E> prev, E item, MyLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedList.Itr();
    }

    /**
     * Itr.
     */
    private class Itr implements Iterator<E> {
        /**
         */
        private MyLinkedList.Node cursor;
        /**
         */
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return this.nextIndex < MyLinkedList.this.size;
        }

        @Override
        public E next() {
            if (this.nextIndex >= MyLinkedList.this.size) {
                throw new NoSuchElementException();
            }

            this.cursor = this.cursor == null ? MyLinkedList.this.first : this.cursor.next;
            ++nextIndex;

            return (E) cursor.item;
        }
    }
}