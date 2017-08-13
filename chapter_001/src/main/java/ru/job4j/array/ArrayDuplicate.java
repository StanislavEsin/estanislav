package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * remove.
     * @param array - String[]
     * @return return - String[]
     */
    public String[] remove(String[] array) {
        int duplicate = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }

            for (int j = 0; j < array.length; j++) {
                if (i == j) {
                    continue;
                }

                if (array[i].equals(array[j])) {
                    array[j] = null;
                    duplicate++;
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] == null) {
                    for (int k = j + 1; k < array.length; k++) {
                        if (array[k] != null) {
                            String tmp = array[k];
                            array[k] = array[j];
                            array[j] = tmp;
                            break;
                        }
                    }

                }
            }
        }
        return Arrays.copyOf(array, array.length - duplicate);
    }
}