package ru.job4j;

/**
 * Calculator.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Calculator {
    /**
     */
    private double result;

    /**
     * add.
     * @param first - double
     * @param second - double
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * subtract.
     * @param first - double
     * @param second - double
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * div.
     * @param first - double
     * @param second - double
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiple.
     * @param first - double
     * @param second - double
     */
	public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * getResult.
     * @return return - double
     */
    public double getResult() {
        return this.result;
    }
}