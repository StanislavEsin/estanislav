package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertList.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * toList.
     * @param array - int[][]
     * @return return - List<Integer>
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }

        return list;
    }

    /**
     * toArray.
     * @param list - List<Integer>
     * @param rows - int
     * @return return - int[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];

        int currentRow = 1; int currentColumn = 1;
        for (int i = 0; i < rows * rows; i++) {
            array[currentRow - 1][currentColumn - 1] = i < list.size() ? list.get(i) : 0;

            if (currentColumn % rows == 0) {
                currentRow++;
                currentColumn = 1;
            } else {
                currentColumn++;
            }
        }

        return array;
    }
}