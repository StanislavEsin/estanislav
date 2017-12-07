package ru.job4j.ui;

import ru.job4j.dao.Order;

/**
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
    void execute(Input input, Order tracker);

    /**
     * ask.
     * @return String
     */
    String info();
}