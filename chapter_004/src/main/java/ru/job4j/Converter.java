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
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            @Override
            public Integer next() {
                return it.next().next();
            }
        };
    }
}