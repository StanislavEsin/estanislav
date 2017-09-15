package ru.job4j.generic;

/**
 * Base.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public abstract class Base {
    /**
     * getId.
     * @return return - String
     */
    abstract String getId();

    /**
     * setId.
     * @param id - String
     */
    abstract void setId(String id);

    /**
     * setName.
     * @param name - String
     */
    abstract void setName(String name);

    /**
     * getName.
     * @return return - String
     */
    abstract String getName();
}