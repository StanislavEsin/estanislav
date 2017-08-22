package ru.job4j;

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
     * массив то что вводит пользователь.
     */
    private int[] intAnswers;
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
     * @see
     * @param answers - String[]
     */
    public StubInput(int[] answers) {
        this.intAnswers = answers;
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

    /**
     * ask.
     *
     * @param question - String
     * @param range    - int[]
     * @return int
     */
    @Override
    public int ask(String question, int[] range) {
        return this.intAnswers[this.position++];
    }
}