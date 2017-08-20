package ru.job4j.strategy;

/**
 * Paint.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Paint {
    /**
     */
    private Shape strategy;

    /**
     * setStrategy.
     * @param strategy - Shape
     */
    public void setStrategy(Shape strategy) {
        this.strategy = strategy;
    }

    /**
     * draw.
     */
    public void draw() {
        if (this.strategy != null) {
            System.out.println(this.strategy.pic());
        }
    }
}