package ru.job4j.map;

import java.util.Calendar;

/**
 * User.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 20.09.2017
 */
public class User {
    /**
     */
    private String name;
    /**
     */
    private int children;
    /**
     */
    private Calendar birthday;

    /**
     * Constructor.
     * @param name - String
     * @param children - int
     * @param birthday - Calendar
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday + '}';
    }
}