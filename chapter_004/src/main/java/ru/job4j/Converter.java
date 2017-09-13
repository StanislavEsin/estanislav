package ru.job4j;

import java.util.Iterator;

/**
 * Converter.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class Converter {
    /**
     * convert.
     * @param it - Iterator<Iterator<Integer>>
     * @return return - Iterator<Integer>
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> currentIterator = null;

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext() || it.hasNext();
            }

            @Override
            public Integer next() {
                if (currentIterator == null || !currentIterator.hasNext()) {
                    if (!it.hasNext()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    currentIterator = it.next();
                }

                return currentIterator.next();
            }
        };
    }
}