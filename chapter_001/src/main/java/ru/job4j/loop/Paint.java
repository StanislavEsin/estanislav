package ru.job4j.loop;

/**
 * Board.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * piramid.
     * @param h - int
     * @return return - String
     */
    public String piramid(int h) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h * 2; j++) {
                if (j > h - 2 - i && j < h + i) {
                    builder.append("^");
                } else if (j < h * 2 - 1) {
                    builder.append(" ");
                }
            }
            if (i < h - 1) {
                builder.append(System.getProperty("line.separator"));
            }
        }
        return builder.toString();
    }
}