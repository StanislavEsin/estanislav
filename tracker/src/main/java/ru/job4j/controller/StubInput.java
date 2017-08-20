package ru.job4j.controller;

/**
 * StubInput - используется для подмены ввода пользовательских данных из консоли.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StubInput implements Input {
    /**
     * массив то что вводит пользователь.
     */
    private String[] answers;
    /**
     */
    private int position = 0;

    /**
     * @see
     * @param answers - String[]
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * ask.
     * @param question - String
     * @return String
     */
    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }
}