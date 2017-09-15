package ru.job4j.generic;

/**
 * Role.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class Role extends Base {
    /**
     */
    private String id;
    /**
     */
    private String name;

    /**
     * Constructor.
     * @param id - String
     * @param name - String
     */
    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }

    /**
     * setName.
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    String getId() {
        return id;
    }

    @Override
    void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}