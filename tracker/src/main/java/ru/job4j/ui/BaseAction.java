package ru.job4j.ui;

/**
 * BaseAction.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public abstract class BaseAction implements UserAction {
    /**
     */
    private String name;
    /**
     */
    private int key;

    /**
     * BaseAction.
     * @param name - String
     * @param key - int
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    @Override
    public int key() {
        return this.key;
    }
}