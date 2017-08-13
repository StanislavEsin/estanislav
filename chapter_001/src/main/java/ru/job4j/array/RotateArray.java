package ru.job4j.array;

/**
 * RotateArray.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class RotateArray {
    /**
     * rotate.
     * @param array - int[][]
     * @return return - int[][]
     */
    public int[][] rotate(int[][] array) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < array.length / 2; j++) {
                for (int k = 0; k < array.length - 1 - i; k++) {
                    int tmp = array[j][k];
                    array[j][k] = array[array.length - 1 - k][j];
                    array[array.length - 1 - k][j] = array[array.length - 1 - j][array.length - 1 - k];
                    array[array.length - 1 - j][array.length - 1 - k] = array[k][array.length - 1 - j];
                    array[k][array.length - 1 - j] = tmp;
                }
            }
        }
        return array;
    }
}