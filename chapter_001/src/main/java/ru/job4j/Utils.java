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
}