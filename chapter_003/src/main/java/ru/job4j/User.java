package ru.job4j;

/**
 * User.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable {
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
     */
    private int age;

    /**
     * Constructor.
     * @param id - int
     * @param name - String
     * @param city - String
     * @param age - int
     */
    public User(int id, String name, String city, int age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
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

    /**
     * getAge.
     * @return return - int
     */
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='"
                + name + '\'' + ", city='" + city
                + '\'' + ", age='" + age + '\'' + '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.valueOf(this.age).compareTo(((User) o).getAge());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (age != user.age) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }

        return city != null ? city.equals(user.city) : user.city == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}