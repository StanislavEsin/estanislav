package ru.job4j.loop;

/**
 * Board.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * multiple.
     * @param width - int
     * @param height - int
     * @return return - String
     */
    public String paint(int width, int height) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (i % 2 == 0) {
                    result.append(j % 2 == 0 ? "x" : " ");
                } else {
                    result.append(j % 2 == 0 ? " " : "x");
                }
            }
            result.append("\r\n");
        }
        return result.toString();
    }
}
