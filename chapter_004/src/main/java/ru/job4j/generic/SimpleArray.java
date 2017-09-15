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
    private T[] array;
    /**
     */
    private int index = 0;

    /**
     * Constructor.
     * @param size - int
     */
    public SimpleArray(int size) {
        this.array = (T[]) new Object[size];
    }

    /**
     * add.
     * @param value - T
     * @return return - boolean
     */
    public boolean add(T value) {
        boolean returnValue = true;

        if (this.index == this.array.length) {
            returnValue = false;
        } else {
            this.array[this.index++] = value;
        }

        return returnValue;
    }

    /**
     * update.
     * @param position - int
     * @param value - T
     * @return return - boolean
     */
    public boolean update(int position, T value) {
        boolean returnValue = true;

        if (position >= this.array.length) {
            returnValue = false;
        } else {
            this.array[position] = value;
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
     * @param value - T
     * @return return - boolean
     */
    public boolean delete(T value) {
        boolean returnValue = false;

        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && this.array[i].equals(value)) {
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
        T returnValue;

        if (position < 0 || position >= this.array.length) {
            returnValue = null;
        } else {
            returnValue = this.array[position];
        }

        return returnValue;
    }

    /**
     * size.
     * @return return - int
     */
    public int size() {
        return this.index - 1;
    }
}