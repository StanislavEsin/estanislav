package ru.job4j;

/**
 * @see
 *
 * UserAction.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public interface UserAction {
    /**
     * ask.
     * @return int
     */
    int key();

    /**
     * ask.
     * @param input - Input
     * @param tracker - Tracker
     */
    void execute(Input input, Tracker tracker);
    /**
     * ask.
     * @return String
     */
    String info();
}