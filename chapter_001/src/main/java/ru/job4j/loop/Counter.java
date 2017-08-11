package ru.job4j.loop;

/**
 * Counter.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * multiple.
     * @param start - int
     * @param finish - int
     * @return return - int
     */
    public int add(int start, int finish) {
        int summ = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                summ += i;
            }
        }
        return summ;
    }
}
