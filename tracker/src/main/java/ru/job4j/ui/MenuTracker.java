package ru.job4j.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.dao.DAOException;
import ru.job4j.dao.Order;
import ru.job4j.domain.Item;

import java.util.Date;
import java.util.List;

/**
 * MenuTracker.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class MenuTracker {
    protected static final Logger LOG = LoggerFactory.getLogger(MenuTracker.class);
    /**
     */
    private Input input;
    /**
     */
    private Order tracker;
    /**
     */
    private UserAction[] actions = new UserAction[6];

    /**
     * MenuTracker.
     * @param input - Input
     * @param tracker - Tracker
     */
    public MenuTracker(Input input, Order tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * fillActions.
     */
    public void fillActions() {
        this.actions[0] = new AddNewItem("Add new Item", 1);
        this.actions[1] = new ShowAllItems("Show all items", 2);
        this.actions[2] = new EditItem("Edit item", 3);
        this.actions[3] = new DeleteItem("Delete item", 4);
        this.actions[4] = new BaseAction("Find item by Id", 5) {
            @Override
            public void execute(Input input, Order tracker) {
                String id = input.ask("Enter id item: ");
                Item findItem = null;
                try {
                    findItem = tracker.findById(id);
                    System.out.println(findItem);
                } catch (DAOException e) {
                    LOG.error("Error find by id {} item", id, e);
                }
            }
        };
        this.actions[5] = new FindItemsByName("Find items by name", 6);
    }

    /**
     * select.
     * @param key - int
     */
    public void select(int key) {
        this.actions[key - 1].execute(this.input, this.tracker);
    }

    /**
     * show.
     */
    public void show() {
        for (UserAction action: this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * getRanges.
     * @return int[]
     */
    public int[] getRanges() {
        int[] ranges = new int[actions.length];

        for (int i = 0; i < actions.length; i++) {
            ranges[i] = actions[i].key();
        }

        return ranges;
    }

    /**
     * AddNewItem.
     */
    private class AddNewItem extends BaseAction {
        /**
         * AddNewItem.
         * @param name - String
         * @param key - int
         */
        AddNewItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Order tracker) {
            String name = input.ask("Enter name item: ");
            String desc = input.ask("Enter description item: ");

            Item newItem = new Item(name, desc, new Date().getTime());
            try {
                tracker.add(newItem);
            } catch (DAOException e) {
                LOG.error("Error add item", e);
            }
        }
    }

    /**
     * ShowAllItems.
     */
    private static class ShowAllItems extends BaseAction {
        /**
         * ShowAllItems.
         * @param name - String
         * @param key  - int
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Order tracker) {
            List<Item> findItems = null;
            try {
                findItems = tracker.getAll();
                findItems.forEach(System.out::println);
            } catch (DAOException e) {
                LOG.error("Error get all items", e);
            }
        }
    }

    /**
     * DeleteItem.
     */
    private class DeleteItem extends BaseAction {
        /**
         * DeleteItem.
         * @param name - String
         * @param key  - int
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Order tracker) {
            String id = input.ask("Enter id item that you want to delete: ");
            Item findItem = null;
            try {
                findItem = tracker.findById(id);
                tracker.delete(findItem);
            } catch (DAOException e) {
                LOG.error("Error delete item", e);
            }
        }
    }

    /**
     * FindItemsByName.
     */
    private class FindItemsByName extends BaseAction {
        /**
         * FindItemsByName.
         * @param name - String
         * @param key  - int
         */
        FindItemsByName(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Order tracker) {
            String name = input.ask("Enter name item: ");
            List<Item> findItems = null;
            try {
                findItems = tracker.findByName(name);
                findItems.forEach(System.out::println);
            } catch (DAOException e) {
                LOG.error("Error find by name {} item", name, e);
            }
        }
    }
}

/**
 * EditItem.
 */
class EditItem extends BaseAction {
    /**
     * EditItem.
     * @param name - String
     * @param key  - int
     */
    EditItem(String name, int key) {
        super(name, key);
    }

    @Override
    public void execute(Input input, Order tracker) {
        String id = input.ask("Enter id item: ");
        String name = input.ask("Enter name item: ");
        String desc = input.ask("Enter description item: ");

        Item findItem = null;
        try {
            findItem = tracker.findById(id);
            Item item = new Item(id, name, desc, findItem.getCreated());
            tracker.update(item);
        } catch (DAOException e) {
            MenuTracker.LOG.error("Error update items", e);
        }
    }
}