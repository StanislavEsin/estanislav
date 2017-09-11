package ru.job4j;

import java.util.Iterator;

/**
 * IteratorTwoDimensionalArray.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.09.2017
 */
public class IteratorTwoDimensionalArray implements Iterator {
    /**
     */
    private final int[][] array;
    /**
     */
    private int i = 0;
    /**
     */
    private int j = 0;

    /**
     * Constructor.
     * @param array - int[][]
     */
    public IteratorTwoDimensionalArray(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return j != array[i].length || array.length > i + 1;
    }

    @Override
    public Object next() {
        if (j == array[i].length) {
            i++;
            j = 0;
        }

        return array[i][j++];
    }
}