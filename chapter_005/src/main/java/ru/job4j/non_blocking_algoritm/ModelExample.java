package ru.job4j.non_blocking_algoritm;

/**
 * ModelExample.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public class ModelExample extends Model {
    /**
     */
    private volatile String name;

    /**
     * Constructor.
     * @param name - String
     * @param id = int
     */
    public ModelExample(int id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * getName.
     * @return String
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModelExample that = (ModelExample) o;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public String toString() {
        return "ModelExample{"
                + "name='" + name
                + '\''
                + ", id=" + this.getId() + '}';
    }
}