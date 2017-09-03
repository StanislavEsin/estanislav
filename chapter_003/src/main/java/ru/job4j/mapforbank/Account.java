package ru.job4j.mapforbank;

/**
 * Account.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Account {
    /**
     */
    private double value;
    /**
     */
    private String requisites;

    /**
     * Constructor.
     * @param value - double
     * @param requisites - String
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * getValue.
     * @return return - double
     */
    public double getValue() {
        return value;
    }

    /**
     * setValue.
     * @param value - double
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * getRequisites.
     * @return return - String
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * setRequisites.
     * @param requisites - String
     */
    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    @Override
    public int hashCode() {
        return requisites != null ? requisites.hashCode() : 0;
    }
}