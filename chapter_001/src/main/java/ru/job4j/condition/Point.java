package ru.job4j.condition;

/**
 * Point.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Point {
    /**
     */
    private int x;
    /**
     */
    private int y;

    /**
     * Point.
     * @param x - int
     * @param y - int
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getX.
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY.
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * is.
     * @param a - int
     * @param b - int
     * @return boolean
     */
    public boolean is(int a, int b) {
        return this.y == this.x * a + b;
    }
}
