package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenSubtractFiveMinusOneThenFour() {
        Calculator calc = new Calculator();
        calc.subtract(5D, 1D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDivTenDividedFiveThenTwo() {
        Calculator calc = new Calculator();
        calc.div(10D, 5D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenMultipleTenMultiplyFiveThenFifty() {
        Calculator calc = new Calculator();
        calc.multiple(10D, 5D);
        double result = calc.getResult();
        double expected = 50D;
        assertThat(result, is(expected));
    }
}