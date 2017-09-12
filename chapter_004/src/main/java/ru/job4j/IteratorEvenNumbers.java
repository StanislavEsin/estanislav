package ru.job4j;

import java.util.Iterator;

/**
 * IteratorEvenNumbers.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class IteratorEvenNumbers implements Iterator {
    /**
     */
    private final int[] array;
    /**
     */
    private int i = 0;

    /**
     * Constructor.
     * @param array - int[]
     */
    public IteratorEvenNumbers(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean returnResult = false;

        for (int j = i; j < array.length; j++) {
            if (array[j] % 2 == 0) {
                returnResult = true;
                break;
            }
        }

        return returnResult;
    }

    @Override
    public Object next() {
        while (array[i] % 2 != 0) {
            i++;
        }

        return array[i++];
    }
}