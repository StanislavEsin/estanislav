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
    private int row = 0;
    /**
     */
    private int column = 0;

    /**
     * Constructor.
     * @param array - int[][]
     */
    public IteratorTwoDimensionalArray(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return column != array[row].length || array.length > row + 1;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (column == array[row].length) {
            row++;
            column = 0;
        }

        return array[row][column++];
    }
}