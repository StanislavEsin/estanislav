package ru.job4j.monitore;

/**
 * User.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.10.2017
 */
public class User {
    /**
     */
    private int id;
    /**
     */
    private int amount;

    /**
     * Constructor.
     * @param id - int
     * @param amount - int
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * getId.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * getAmount.
     * @return int
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setAmount.
     * @param amount - int
     */
    public void setAmount(int amount) {
        this.amount = amount;
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

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}