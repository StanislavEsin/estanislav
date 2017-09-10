package ru.job4j.units;

import java.util.regex.Pattern;

/**
 * Unit.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Unit implements Comparable {
    /**
     */
    private String name;
    /**
     */
    private String[] levels;

    /**
     * Constructor.
     * @param name - String
     */
    public Unit(String name) {
        this.name = name;
        levels = name.split(Pattern.quote("\\"));
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }

    /**
     * getLevels.
     * @return return - String[]
     */
    public String[] getLevels() {
        return levels;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Unit) o).getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Unit unit = (Unit) o;

        return name != null ? name.equals(unit.name) : unit.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}