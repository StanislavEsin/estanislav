package ru.job4j;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Units.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Units {
    /**
     * sorting.
     * @param array - List<String>
     * @param sortingMethod - SortingMethod
     * @return return - List<String>
     */
    public List<String> sorting(List<String> array, SortingMethod sortingMethod) {
        List<String> returnArray = addCodesOfUpperLevelUnits(array);

        if (sortingMethod == SortingMethod.ASCENDING) {
            Collections.sort(returnArray);
        } else if (sortingMethod == SortingMethod.DESCENDING) {
            Collections.sort(returnArray, new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    int result = 0;

                    String[] sLevel = s.split(Pattern.quote("\\"));
                    String[] t1Level = t1.split(Pattern.quote("\\"));

                    int i = 0;

                    while (result == 0 && t1Level.length > i && sLevel.length > i) {
                        result = t1Level[i].compareTo(sLevel[i]);
                        i++;
                    }

                    return result;
                }
            });
        }

        return returnArray;
    }

    /**
     * addCodesOfUpperLevelUnits.
     * @param array - List<String>
     * @return return - List<String>
     */
    private List<String> addCodesOfUpperLevelUnits(List<String> array) {
        List<String> correctArray = new ArrayList<>();

        for (String value : array) {
            String[] levels = value.split(Pattern.quote("\\"));

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < levels.length; i++) {
                if (i != 0) {
                    builder.append("\\");
                }

                builder.append(levels[i]);

                if (!correctArray.contains(builder.toString())) {
                    correctArray.add(builder.toString());
                }
            }
        }

        return correctArray;
    }
}