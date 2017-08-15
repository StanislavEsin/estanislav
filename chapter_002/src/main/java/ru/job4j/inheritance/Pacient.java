package ru.job4j.inheritance;

/**
 * Pacient - пациент.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Pacient {
    /**
     */
    private String name;

    /**
     * Constructor
     * @param name - String
     */
    public Pacient(String name) {
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