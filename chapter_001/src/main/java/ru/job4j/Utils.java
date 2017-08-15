package ru.job4j;

/**
 * Utils.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Utils {
    /**
     * contains - определяет, содержит ли строка origin последовательность символов в sub.
     * @param origin - строка в которой ищем.
     * @param sub - подстрока которую ищем.
     * @return return - boolean
     */
    boolean contains(String origin, String sub) {
        char[] charsOrigin = origin.toCharArray();
        char[] charsSub = sub.toCharArray();

        int numberContains = 0;
        for (int i = 0; i < charsOrigin.length; i++) {
            if (numberContains == charsSub.length) {
                return true;
            }

            if (charsOrigin[i] == charsSub[numberContains]) {
                numberContains++;
            } else {
                numberContains = 0;
            }
        }
        return false;
    }

    /**
     * merge - cлияние двух упорядоченных массивов.
     * @param arrayA - int[].
     * @param arrayB - int[].
     * @return return - merge int[].
     */
    public int[] merge(int[] arrayA, int[] arrayB) {
        int[] result = new int[arrayA.length + arrayB.length];

        int i = 0;
        int j = 0;
        int index = 0;

        while (i < arrayA.length && j < arrayB.length) {
            if (arrayA[i] < arrayB[j]) {
                result[index] = arrayA[i];
                i++;
            } else {
                result[index] = arrayB[j];
                j++;
            }
            index++;
        }

        if (i < arrayA.length) {
            System.arraycopy(arrayA, i, result, index, arrayA.length - i);
        }

        if (j < arrayB.length) {
            System.arraycopy(arrayB, j, result, index, arrayB.length - j);
        }

        return result;
    }
}