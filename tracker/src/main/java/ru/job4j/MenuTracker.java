package ru.job4j;

import java.util.Date;

/**
 * MenuTracker.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class MenuTracker {
    /**
     */
    private Input input;
    /**
     */
    private Tracker tracker;
    /**
     */
    private UserAction[] actions = new UserAction[6];

    /**
     * @see
     * @param input - Input
     * @param tracker - Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
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
            public void execute(Input input, Tracker tracker) {
                String id = input.ask("Enter id item: ");
                Item findItem = tracker.findById(id);
                System.out.println(findItem);
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
         * @see
         * @param name - String
         * @param key - int
         */
        AddNewItem(String name, int key) {
            super(name, key);
        }

        /**
         * ask.
         * @param input - Input
         * @param tracker - Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name item: ");
            String desc = input.ask("Enter description item: ");

            Item newItem = new Item(name, desc, new Date().getTime());
            tracker.add(newItem);
        }
    }

    /**
     * ShowAllItems.
     */
    private static class ShowAllItems extends BaseAction {
        /**
         * @param name - String
         * @param key  - int
         * @see
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }

        /**
         * ask.
         * @param input - Input
         * @param tracker - Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] findItems = tracker.getAll();

            for (Item item: findItems) {
                System.out.println(item);
            }
        }
    }

    /**
     * DeleteItem.
     */
    private class DeleteItem extends BaseAction {
        /**
         * @param name - String
         * @param key  - int
         * @see
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * ask.
         * @param input - Input
         * @param tracker - Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id item that you want to delete: ");
            Item findItem = tracker.findById(id);

            tracker.delete(findItem);
        }
    }

    /**
     * FindItemsByName.
     */
    private class FindItemsByName extends BaseAction {
        /**
         * @param name - String
         * @param key  - int
         * @see
         */
        FindItemsByName(String name, int key) {
            super(name, key);
        }

        /**
         * ask.
         * @param input - Input
         * @param tracker - Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name item: ");
            Item[] findItems = tracker.findByName(name);

            for (Item item: findItems) {
                System.out.println(item);
            }
        }
    }
}

/**
 * EditItem.
 */
class EditItem extends BaseAction {
    /**
     * @param name - String
     * @param key  - int
     * @see
     */
    EditItem(String name, int key) {
        super(name, key);
    }

    /**
     * ask.
     * @param input - Input
     * @param tracker - Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter id item: ");
        String name = input.ask("Enter name item: ");
        String desc = input.ask("Enter description item: ");

        Item findItem = tracker.findById(id);
        Item item = new Item(name, desc, findItem.getCreated());
        item.setId(id);
        tracker.update(item);
    }
}