package ru.job4j.max;

/**
 * Max.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * max.
     * @param first - int
     * @param second - int
     * @return int
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * max.
     * @param first - int
     * @param second - int
     * @param third - int
     * @return int
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}