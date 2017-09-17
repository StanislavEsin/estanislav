package ru.job4j.list;

/**
 * Node.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 17.09.2017
 */
public class Node<T> {
    /**
     */
    public T value;
    /**
     */
    public Node<T> next;

    /**
     * Constructor.
     * @param value - T
     */
    public Node(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node<?> node = (Node<?>) o;

        return value != null ? value.equals(node.value) : node.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}