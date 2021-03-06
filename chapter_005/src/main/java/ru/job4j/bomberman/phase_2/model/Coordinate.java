package ru.job4j.bomberman.phase_2.model;

/**
 * Coordinate.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public class Coordinate {
    /**
     */
    private final int x;
    /**
     */
    private final int y;

    /**
     * Constructor.
     * @param x - int.
     * @param y - int.
     */
    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * getY.
     * @return int.
     */
    public int getY() {
        return this.y;
    }

    /**
     * getX.
     * @return int.
     */
    public int getX() {
        return this.x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;

        if (x != that.x) {
            return false;
        }
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "y=" + y
                + ", x=" + x + '}';
    }
}