package ru.job4j.dao.ram;

import ru.job4j.dao.Order;
import ru.job4j.domain.Item;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tracker - хранилище для заявок.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Tracker implements Order {
    /**
     * массив заявок.
     */
    private List<Item> items = new ArrayList<>();

    @Override
    public Item add(Item item) {
        items.add(item);
        return item;
    }

    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
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

    @Override
    public List<Item> findByName(String key) {
        List<Item> tmp = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().equals(key)) {
                tmp.add(item);
            }
        }

        return tmp;
    }

    @Override
    public void delete(Item item) {
        Iterator<Item> itemIterator = items.iterator();

        while (itemIterator.hasNext()) {
            if (itemIterator.next().getId().equals(item.getId())) {
                itemIterator.remove();
            }
        }
    }

    @Override
    public void update(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(item.getId())) {
                items.set(i, item);
                break;
            }
        }
    }
}