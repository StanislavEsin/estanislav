package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTree.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 23.09.2017
 */
public class BinaryTree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     */
    private Node<E> root;

    /**
     * Node.
     * @param <E>
     */
    private class Node<E> {
        /**
         */
        Node<E> right;
        /**
         */
        Node<E> left;
        /**
         */
        E value;
    }

    /**
     * add.
     *
     * @param parent - parent.
     * @param child - child.
     * @return - boolean
     */
    @Override
    public boolean add(E parent, E child) {
        throw new UnsupportedOperationException();
    }

    /**
     * add.
     *
     * @param child - child.
     * @return - boolean
     */
    public boolean adds(E child) {
        boolean result;

        if (this.root == null) {
            this.root = createNode(child);
            result = true;
        } else {
            result = insert(this.root, child);
        }

        return result;
    }

    /**
     * insert.
     *
     * @param parent - parent.
     * @param child - child.
     * @return - boolean
     */
    private boolean insert(Node<E> parent, E child) {
        boolean result = false;

        int compare = child.compareTo(parent.value);

        if (compare > 0) {
            if (parent.right == null) {
                parent.right = createNode(child);
                result = true;
            } else {
                result = insert(parent.right, child);
            }
        } else if (compare < 0) {
            if (parent.left == null) {
                parent.left = createNode(child);
                result = true;
            } else {
                result = insert(parent.left, child);
            }
        }

        return result;
    }

    /**
     * createNode.
     * @param value - E
     * @return return - Node<E>
     */
    private Node<E> createNode(E value) {
        Node<E> newNode = new Node<>();
        newNode.value = value;

        return newNode;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        List<E> list = new LinkedList<>();

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }

            list.add(node.value);
        }

        return list.iterator();
    }
}