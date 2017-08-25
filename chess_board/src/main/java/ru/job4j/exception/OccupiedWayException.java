package ru.job4j.exception;

/**
 * OccupiedWayException - путь занят фигурами.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class OccupiedWayException extends Exception {
    /**
     * @param s - String
     */
    public OccupiedWayException(String s) {
        super(s);
    }
}