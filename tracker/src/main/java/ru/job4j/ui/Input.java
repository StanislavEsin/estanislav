package ru.job4j.ui;

/**
 * Input.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public interface Input {
    /**
     * ask.
     * @param question - String
     * @return String
     */
    String ask(String question);

    /**
     * ask.
     * @param question - String
     * @param range - int[]
     * @return int
     */
    int ask(String question, int[] range);
}