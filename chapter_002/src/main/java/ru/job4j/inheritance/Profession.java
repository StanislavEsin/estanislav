package ru.job4j.inheritance;

/**
 * Profession - профессия.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Profession {
    /**
     */
    private String name;

    /**
     * Constructor
     * @param name - String
     */
    public Profession(String name) {
        this.name = name;
    }

    /**
     * eat - есть.
     * @param food - Food
     */
    public void eat(Food food) {
        System.out.println("Едим: " + food.getName());
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }
}