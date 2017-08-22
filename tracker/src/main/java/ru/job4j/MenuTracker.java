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
        this.actions[0] = new AddNewItem();
        this.actions[1] = new ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemsByName();
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
    private class AddNewItem implements UserAction {
        /**
         * ask.
         * @return int
         */
        @Override
        public int key() {
            return 1;
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

        /**
         * ask.
         * @return String
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }

    /**
     * ShowAllItems.
     */
    private static class ShowAllItems implements UserAction {
        /**
         * ask.
         * @return int
         */
        @Override
        public int key() {
            return 2;
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

        /**
         * ask.
         * @return String
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * DeleteItem.
     */
    private class DeleteItem implements UserAction {
        /**
         * ask.
         * @return int
         */
        @Override
        public int key() {
            return 4;
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

        /**
         * ask.
         * @return String
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * FindItemById.
     */
    private class FindItemById implements UserAction {
        /**
         * ask.
         * @return int
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * ask.
         * @param input - Input
         * @param tracker - Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id item: ");
            Item findItem = tracker.findById(id);
            System.out.println(findItem);
        }

        /**
         * ask.
         * @return String
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * FindItemsByName.
     */
    private class FindItemsByName implements UserAction {
        /**
         * ask.
         * @return String
         */
        @Override
        public int key() {
            return 6;
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

        /**
         * ask.
         * @return String
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}

/**
 * EditItem.
 */
class EditItem implements UserAction {
    /**
     * ask.
     * @return String
     */
    @Override
    public int key() {
        return 3;
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

    /**
     * ask.
     * @return String
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}