package ru.job4j.exception;

/**
 * ImpossibleMoveException - фигура не может так двигаться.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class ImpossibleMoveException extends Exception {
    /**
     * @param s - String
     */
    public ImpossibleMoveException(String s) {
        super(s);
    }
}