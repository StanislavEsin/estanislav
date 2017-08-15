package ru.job4j.inheritance;

/**
 * Engineer - инженер.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
    /**
     */
    private int digit;

    /**
     * Constructor
     * @param name - String
     * @param digit - int
     */
    public Engineer(String name, int digit) {
        super(name);
        this.digit = digit;
    }

    /**
     * work.
     * @param order - Order
     */
    public void work(Order order) {
        System.out.println("Инженер " + this.getName() + " работает по заказу №" + order.getName());
    }
}