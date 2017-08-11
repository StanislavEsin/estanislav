package ru.job4j.loop;

/**
 * Factorial.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * calc.
     * @param n - int
     * @return return - int
     */
    public int calc(int n) {
        if (n <= 0) {
            return 1;
        }

        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
