package ru.job4j.exception;

/**
 * FigureNotFoundException - в заданной ячейки нету фигуры.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class FigureNotFoundException extends Exception {
    /**
     * @param s - String
     */
    public FigureNotFoundException(String s) {
        super(s);
    }
}