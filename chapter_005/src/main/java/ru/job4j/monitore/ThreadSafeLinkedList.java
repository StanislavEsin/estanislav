package ru.job4j.monitore;

/**
 * ThreadSafeLinkedList.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class ThreadSafeLinkedList<E> {
    /**
     */
    private int size = 0;
    /**
     */
    private ThreadSafeLinkedList.Node<E> first;
    /**
     */
    private ThreadSafeLinkedList.Node<E> last;

    /**
     * add.
     * @param item - E
     * @return return - boolean
     */
    public synchronized boolean add(E item) {
        ThreadSafeLinkedList.Node last = this.last;
        ThreadSafeLinkedList.Node newNode = new ThreadSafeLinkedList.Node(last, item, null);
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
    public synchronized E get(int index) {
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
    public synchronized boolean remove(E item) {
        boolean result = false;

        ThreadSafeLinkedList.Node node;
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
    private void unlink(ThreadSafeLinkedList.Node<E> node) {
        ThreadSafeLinkedList.Node<E> tmpNodePrev = node.prev;
        ThreadSafeLinkedList.Node<E> tmpNodeNext = node.next;

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
    private ThreadSafeLinkedList.Node<E> node(int index) {
        ThreadSafeLinkedList.Node var2;
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
        private ThreadSafeLinkedList.Node<E> next;
        /**
         */
        private ThreadSafeLinkedList.Node<E> prev;

        /**
         * Constructor.
         * @param prev - MyLinkedList.Node<E>
         * @param item - E
         * @param next - MyLinkedList.Node<E>
         */
        private Node(ThreadSafeLinkedList.Node<E> prev, E item, ThreadSafeLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}