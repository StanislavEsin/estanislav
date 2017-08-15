package ru.job4j.inheritance;

/**
 * Food - еда.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Food {
    /**
     */
    private String name;

    /**
     * Constructor
     * @param name - String
     */
    public Food(String name) {
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