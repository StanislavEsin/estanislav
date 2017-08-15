package ru.job4j.inheritance;

/**
 * Order - заказ.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Order {
    /**
     */
    private String name;

    /**
     * Constructor
     * @param name - String
     */
    public Order(String name) {
        this.name = name;
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }
}