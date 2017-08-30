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

        for (int[] row : array) {
            for (int column : row) {
                list.add(column);
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

        for (Integer value : list) {
            if (currentRow > rows) {
                break;
            }

            array[currentRow - 1][currentColumn - 1] = value == null ? 0 : value;
            if (currentColumn % rows == 0) {
                currentRow++;
                currentColumn = 1;
            } else {
                currentColumn++;
            }
        }

        if (currentRow != rows || currentColumn != rows) {
            for (int i = currentRow; i <= rows; i++) {
                for (int j = currentColumn; j <= rows; j++) {
                    array[currentRow - 1][currentColumn - 1] = 0;
                    if (currentColumn % rows == 0) {
                        currentRow++;
                        currentColumn = 1;
                    } else {
                        currentColumn++;
                    }
                }
            }
        }

        return array;
    }

    /**
     * convert.
     * @param list - List<int[]>
     * @return return - List<Integer>
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> returnList = new ArrayList<>();

        for (int[] valList : list) {
            for (int valArray : valList) {
                returnList.add(valArray);
            }
        }

        return returnList;
    }
}