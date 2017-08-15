package ru.job4j.inheritance;

/**
 * ClassRoom - учебный класс.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ClassRoom {
    /**
     */
    private String name;

    /**
     * Constructor
     * @param name - String
     */
    public ClassRoom(String name) {
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