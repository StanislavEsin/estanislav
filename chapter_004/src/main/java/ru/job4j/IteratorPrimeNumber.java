package ru.job4j;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * IteratorPrimeNumber.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class IteratorPrimeNumber implements Iterator {
    /**
     */
    private final int[] array;
    /**
     */
    private int index = 0;
    /**
     */
    private Map<Integer, Boolean> cache = new HashMap<>();

    /**
     * Constructor.
     * @param array - int[]
     */
    public IteratorPrimeNumber(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean returnHasNext = false;

        for (int j = index; j < array.length; j++) {
            if (checkPrimeNumber(array[j])) {
                returnHasNext = true;
                break;
            }
        }

        return returnHasNext;
    }

    @Override
    public Object next() {
        for (int j = index; j < array.length; j++) {
            if (checkPrimeNumber(array[j])) {
                index = j;
                return array[index++];
            }
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * checkPrimeNumber.
     * @param number - int
     * @return return - boolean
     */
    private boolean checkPrimeNumber(int number) {
        Boolean cacheValue = cache.get(number);
        Boolean primeNumber = cacheValue != null ? cacheValue : number > 2;

        if (cacheValue == null) {
            for (int j = 2; j < number; j++) {
                if (number % j == 0) {
                    primeNumber = false;
                    break;
                }
            }

            cache.put(number, primeNumber);
        }
        return primeNumber;
    }
}