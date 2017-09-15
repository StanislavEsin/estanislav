package ru.job4j.generic;

/**
 * Store.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public interface Store<T extends Base> {
    /**
     * add.
     * @param value - T
     * @return return - boolean
     */
    boolean add(T value);

    /**
     * update.
     * @param value - T
     * @return return - boolean
     */
    boolean update(T value);

    /**
     * delete.
     * @param value - T
     * @return return - boolean
     */
    boolean delete(T value);

    /**
     * findById.
     * @param id - String
     * @return return - Base
     */
    Base findById(String id);
}