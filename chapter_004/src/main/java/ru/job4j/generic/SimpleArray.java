package ru.job4j.generic;

/**
 * SimpleArray.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.09.2017
 */
public class SimpleArray<T> {
    /**
     */
    private Object[] array;
    /**
     */
    private int index = 0;

    /**
     * Constructor.
     * @param size - int
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * add.
     * @param v - T
     * @return return - boolean
     */
    public boolean add(T v) {
        boolean returnValue = true;

        if (this.index == this.array.length) {
            returnValue = false;
        } else {
            this.array[this.index++] = v;
        }

        return returnValue;
    }

    /**
     * update.
     * @param position - int
     * @param v - T
     * @return return - boolean
     */
    public boolean update(int position, T v) {
        boolean returnValue = true;

        if (position >= this.array.length) {
            returnValue = false;
        } else {
            this.array[position] = v;
        }

        return returnValue;
    }

    /**
     * delete.
     * @param position - int
     * @return return - boolean
     */
    public boolean delete(int position) {
        boolean returnValue = true;

        if (position >= this.array.length) {
            returnValue = false;
        } else {
            if (position == this.index - 1) {
                this.array[this.index--] = null;
            } else {
                this.array[position] = this.array[--this.index];
                this.array[this.index + 1] = null;
            }
        }

        return returnValue;
    }

    /**
     * delete.
     * @param v - T
     * @return return - boolean
     */
    public boolean delete(T v) {
        boolean returnValue = false;

        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && this.array[i].equals(v)) {
                if (i == this.index - 1) {
                    this.array[this.index--] = null;
                } else {
                    this.array[i] = this.array[--this.index];
                    this.array[this.index] = null;
                }

                returnValue = true;
            }
        }

        return returnValue;
    }

    /**
     * get.
     * @param position - int
     * @return return - T
     */
    public T get(int position) {
        return position >= this.array.length ? null : (T) this.array[position];
    }
}