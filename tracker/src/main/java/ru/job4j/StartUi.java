package ru.job4j;

import java.util.Date;

/**
 * StartUi.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StartUi {
    /**
     */
    private String[] menu;
    /**
     */
    private Input input;
    /**
     */
    private Tracker tracker;
    /**
     */
    private static final int ADD = 0;
    /**
     */
    private static final int SHOW = 1;
    /**
     */
    private static final int EDIT = 2;
    /**
     */
    private static final int DELETE = 3;
    /**
     */
    private static final int FIND_BY_ID = 4;
    /**
     */
    private static final int FIND_BY_NAME = 5;
    /**
     */
    private static final int EXIT = 6;

    /**
     * @see
     * @param input - Input
     * @param tracker - Tracker
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;

        this.menu = new String[7];
        this.menu[0] = "0. Add new Item";
        this.menu[1] = "1. Show all items";
        this.menu[2] = "2. Edit item";
        this.menu[3] = "3. Delete item";
        this.menu[4] = "4. Find item by Id";
        this.menu[5] = "5. Find items by name";
        this.menu[6] = "6. Exit Program";
    }

    /**
     * showMenu.
     */
    private void showMenu() {
        System.out.println("---------------------");
        for (String positionMenu: this.menu) {
            System.out.println(positionMenu);
        }
        System.out.println("---------------------");
    }

    /**
     * init.
     */
    public void init() {
        while (true) {
            showMenu();

            int inputIndex;
            try {
                inputIndex = Integer.parseInt(this.input.ask("Select: "));
            } catch (NumberFormatException e) {
                continue;
            }

            if (inputIndex == ADD) {
                createItem(this.input, this.tracker);
            } else if (inputIndex == SHOW) {
                showAllItems(this.tracker);
            } else if (inputIndex == EDIT) {
                editItem(this.input, this.tracker);
            } else if (inputIndex == DELETE) {
                deleteItem(this.input, this.tracker);
            } else if (inputIndex == FIND_BY_ID) {
                findItemById(this.input, this.tracker);
            } else if (inputIndex == FIND_BY_NAME) {
                findItemsByName(this.input, this.tracker);
            } else if (inputIndex == EXIT) {
                break;
            }
        }
    }

    /**
     * createItem.
     * @param input - Input
     * @param tracker - Tracker
     */
    private void createItem(Input input, Tracker tracker) {
        String name = input.ask("Enter name item: ");
        String desc = input.ask("Enter description item: ");

        Item newItem = new Item(name, desc, new Date().getTime());
        tracker.add(newItem);
    }

    /**
     * showAllItems.
     * @param tracker - Tracker
     */
    private void showAllItems(Tracker tracker) {
        Item[] findItems = tracker.getAll();

        for (Item item: findItems) {
            System.out.println("id: " + item.getId()
                    + ", name: " + item.getName()
                    + ", description: " + item.getDesc()
                    + ", created: " + new Date(item.getCreated()));
        }
    }

    /**
     * editItem.
     * @param input - Input
     * @param tracker - Tracker
     */
    private void editItem(Input input, Tracker tracker) {

    }

    /**
     * deleteItem.
     * @param input - Input
     * @param tracker - Tracker
     */
    private void deleteItem(Input input, Tracker tracker) {
        String id = input.ask("Enter id item that you want to delete: ");
        Item findItem = tracker.findById(id);

        tracker.delete(findItem);
    }

    /**
     * findItemById.
     * @param input - Input
     * @param tracker - Tracker
     */
    private void findItemById(Input input, Tracker tracker) {
        String id = input.ask("Enter id item: ");
        Item findItem = tracker.findById(id);
        System.out.println("id: " + findItem.getId()
                + ", name: " + findItem.getName()
                + ", description: " + findItem.getDesc()
                + ", created: " + new Date(findItem.getCreated()));
    }

    /**
     * findItemsByName.
     * @param input - Input
     * @param tracker - Tracker
     */
    private void findItemsByName(Input input, Tracker tracker) {
        String name = input.ask("Enter name item: ");
        Item[] findItems = tracker.findByName(name);

        for (Item item: findItems) {
            System.out.println("id: " + item.getId()
                    + ", name: " + item.getName()
                    + ", description: " + item.getDesc()
                    + ", created: " + new Date(item.getCreated()));
        }
    }

    /**
     * main - точка входа.
     * @param args - String[]
     */
    public static void main(String[] args) {
        new StartUi(new ConsoleInput(), new Tracker()).init();
    }
}