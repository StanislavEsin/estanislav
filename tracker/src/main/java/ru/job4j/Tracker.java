package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Tracker - хранилище для заявок.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Tracker {
    /**
     * массив заявок.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * add - добавление заявок.
     * @param item - Item
     * @return return - Item
     */
    public Item add(Item item) {
        items.add(item);
        return item;
    }

    /**
     * getAll - получение списка всех заявок.
     * @return return - Item[]
     */
    public List<Item> getAll() {
        return items;
    }

    /**
     * findById - получение заявки по id.
     * @param id - String
     * @return return - Item
     */
    public Item findById(String id) {
        Item foundItem = null;

        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                foundItem = item;
                break;
            }
        }

        return foundItem;
    }

    /**
     * findByName - получение списка по имени.
     * @param key - String
     * @return return - Item[]
     */
    public List<Item> findByName(String key) {
        List<Item> tmp = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().equals(key)) {
                tmp.add(item);
            }
        }

        return tmp;
    }

    /**
     * delete - удаление заявок.
     * @param item - Item
     */
    public void delete(Item item) {
        Iterator<Item> itemIterator = items.iterator();

        while (itemIterator.hasNext()) {
            if (itemIterator.next().getId().equals(item.getId())) {
                itemIterator.remove();
            }
        }
    }

    /**
     * update - редактирование заявок.
     * @param item - Item
     */
    public void update(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(item.getId())) {
                items.set(i, item);
                break;
            }
        }
    }
}