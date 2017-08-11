package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {
    /**
     * Test add.
     */
    @Test
    public void whenDistanceSetTwoPointsThenReturnDistanceBetweenPoints() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.distance(a, b);
        double expected = 2D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Test add.
     */
    @Test
    public void whenPeriodSetThreeLengthOfPartiesThenReturnPerimeter() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.period(2D, 2D, 2.8D);
        double expected = 3.4D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();
        double expected = 2D;

        assertThat(result, closeTo(expected, 0.1));
    }
}