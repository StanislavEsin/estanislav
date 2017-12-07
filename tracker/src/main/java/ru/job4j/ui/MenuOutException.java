package ru.job4j.ui;

/**
 * MenuOutException.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class MenuOutException extends RuntimeException {
    /**
     * MenuOutException.
     * @param s - String
     */
    public MenuOutException(String s) {
        super(s);
    }
}