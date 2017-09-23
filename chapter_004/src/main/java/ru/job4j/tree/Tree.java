package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tree.
 * @param <E>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 22.09.2017
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {
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
        List<Node<E>> children;
        /**
         */
        E value;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;

        if (parent == null) {
            this.root = createNode(child);
            result = true;
        } else {
            Queue<Node<E>> queue = new LinkedList<>();
            queue.add(this.root);

            Node<E> findParent = null;

            while (!queue.isEmpty()) {
                Node<E> node = queue.remove();

                if (node.value.equals(child)) {
                    findParent = null;
                    break;
                }

                if (node.value.equals(parent)) {
                    findParent = node;
                }

                queue.addAll(node.children);
            }

            if (findParent != null) {
                findParent.children.add(createNode(child));
                result = true;
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
        newNode.children = new LinkedList<>();

        return newNode;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> list = new LinkedList<>();

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();
            list.add(node.value);
            queue.addAll(node.children);
        }

        return list.iterator();
    }

    /**
     * isBinary.
     * @return return - boolean
     */
    public boolean isBinary() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();

            if (node.children.size() > 2) {
                return false;
            }

            queue.addAll(node.children);
        }

        return true;
    }
}