package ru.job4j.list;

import java.util.EmptyStackException;

/**
 * SimpleStack.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 16.09.2017
 */
public class SimpleStack<T> extends Deque<T> {
    /**
     * Constructor.
     */
    public SimpleStack() {
        super();
    }

    /**
     * poll.
     * @return return - T
     */
    public T poll() {
        if (this.container.size() == 0) {
            throw new EmptyStackException();
        }

        T returnValue = this.container.get(this.container.size() - 1);
        this.container.remove(returnValue);

        return returnValue;
    }
}