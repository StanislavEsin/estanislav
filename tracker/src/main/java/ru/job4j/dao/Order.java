package ru.job4j.dao;

import ru.job4j.domain.Item;
import java.util.List;

/**
 * Order.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public interface Order {
    /**
     * add - добавление заявок.
     * @param item - Item
     * @return return - Item
     */
    Item add(Item item) throws DAOException;

    /**
     * getAll - получение списка всех заявок.
     * @return return - Item[]
     */
    List<Item> getAll() throws DAOException;

    /**
     * findById - получение заявки по id.
     * @param id - String
     * @return return - Item
     */
    Item findById(String id) throws DAOException;

    /**
     * findByName - получение списка по имени.
     * @param key - String
     * @return return - Item[]
     */
    List<Item> findByName(String key) throws DAOException;

    /**
     * delete - удаление заявок.
     * @param item - Item
     */
    void delete(Item item) throws DAOException;

    /**
     * update - редактирование заявок.
     * @param item - Item
     */
    void update(Item item) throws DAOException;
}