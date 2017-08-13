package ru.job4j.array;

/**
 * Turn.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * back.
     * @param array - int[]
     * @return return - int[]
     */
    public int[] back(int[] array) {
        for (int i = 0; i < (array.length / 2); i++) {
            int tmp = array[array.length - (i + 1)];
            array[array.length - (i + 1)] = array[i];
            array[i] = tmp;
        }
        return array;
    }
}