package ru.job4j.list;

/**
 * Deque.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 16.09.2017
 */
public abstract class Deque<T> {
    /**
     */
    protected MyLinkedList<T> container;

    /**
     * Constructor.
     */
    public Deque() {
        this.container = new MyLinkedList<>();
    }

    /**
     * poll.
     * @return return - T
     */
    public abstract T poll();

    /**
     * push.
     * @param value - T
     */
    public void push(T value) {
        container.add(value);
    }

    /**
     * size.
     * @return return - int
     */
    public int size() {
        return this.container.size();
    }
}