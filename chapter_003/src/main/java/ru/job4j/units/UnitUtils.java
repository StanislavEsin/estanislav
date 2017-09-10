package ru.job4j.units;

import java.util.Set;
import java.util.TreeSet;

/**
 * UnitUtils.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UnitUtils {
    /**
     * sortingDescending.
     * @param set - Set<Unit>
     * @return return - Set<Unit>
     */
    public Set<Unit> sortingDescending(Set<Unit> set) {
        set = addCodesOfUpperLevelUnits(set);

        Set<Unit> returnSet = new TreeSet<>((a, b) -> {
            int result = 0;

            String[] aLevels = a.getLevels();
            String[] bLevels = b.getLevels();

            int i = 0;

            while (result == 0 && bLevels.length > i && aLevels.length > i) {
                result = bLevels[i].compareTo(aLevels[i]);
                i++;
            }

            if (result == 0) {
                if (bLevels.length > aLevels.length) {
                    result = -1;
                } else {
                    result = 1;
                }
            }

            return result;
        });

        returnSet.addAll(set);

        return returnSet;
    }

    /**
     * addCodesOfUpperLevelUnits.
     * @param set - Set<Unit>
     * @return return - Set<Unit>
     */
    public Set<Unit> addCodesOfUpperLevelUnits(Set<Unit> set) {
        Set<Unit> correctSet = new TreeSet<>();

        for (Unit value : set) {
            String[] levels = value.getLevels();

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < levels.length; i++) {
                if (i != 0) {
                    builder.append("\\");
                }

                builder.append(levels[i]);

                Unit unit = new Unit(builder.toString());

                if (!correctSet.contains(unit)) {
                    correctSet.add(unit);
                }
            }
        }

        return correctSet;
    }
}