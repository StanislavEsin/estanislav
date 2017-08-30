package ru.job4j;

/**
 * User.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class User {
    /**
     */
    private int id;
    /**
     */
    private String name;
    /**
     */
    private String city;

    /**
     * Constructor.
     * @param id - int
     * @param name - String
     * @param city - String
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * getId.
     * @return return - int
     */
    public int getId() {
        return id;
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }

    /**
     * getCity.
     * @return return - String
     */
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='"
                + name + '\'' + ", city='" + city
                + '\'' + '}';
    }
}