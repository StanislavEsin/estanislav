package ru.job4j.model;

import java.util.Arrays;

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
    private Item[] items = new Item[100];

    /**
     * номер текущей пусто позиции в массиве заявок.
     */
    private int position = 0;

    /**
     * add - добавление заявок.
     * @param item - Item
     * @return return - Item
     */
    public Item add(Item item) {
        this.items[position++] = item;
        return item;
    }

    /**
     * getAll - получение списка всех заявок.
     * @return return - Item[]
     */
    public Item[] getAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * findById - получение заявки по id.
     * @param id - String
     * @return return - Item
     */
    public Item findById(String id) {
        Item foundItem = null;

        for (Item item: this.items) {
            if (item == null) {
                break;
            } else if (item.getId().equals(id)) {
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
    public Item[] findByName(String key) {
        Item[] tmp = new Item[this.items.length];

        int positionTmp = 0;
        for (Item item: this.items) {
            if (item == null) {
                break;
            } else if (item.getName().equals(key)) {
                tmp[positionTmp++] = item;
            }
        }

        return Arrays.copyOf(tmp, positionTmp);
    }

    /**
     * delete - удаление заявок.
     * @param item - Item
     */
    public void delete(Item item) {
        for (int i = 0; i <= position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = this.items[--position];
                this.items[position + 1] = null;
            }
        }
    }

    /**
     * update - редактирование заявок.
     * @param item - Item
     */
    public void update(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                break;
            }

            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = item;
                break;
            }
        }
    }
}