package ru.job4j;

/**
 * MenuOutException.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class MenuOutException extends RuntimeException {
    /**
     * @see
     * @param s - String
     */
    public MenuOutException(String s) {
        super(s);
    }
}