package ru.job4j.controller;

import ru.job4j.model.Tracker;
import ru.job4j.model.Item;
import ru.job4j.view.Menu;

import java.util.Date;

/**
 * StartUi.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StartUI {
    /**
     */
    private Input input;
    /**
     */
    private Tracker tracker;
    /**
     */
    private Menu menu;
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
     * @param menu - Menu
     */
    public StartUI(Input input, Tracker tracker, Menu menu) {
        this.input = input;
        this.tracker = tracker;
        this.menu = menu;
    }

    /**
     * init.
     */
    public void init() {
        while (true) {
            menu.showMenu();

            int inputIndex;
            try {
                inputIndex = Integer.parseInt(this.input.ask("Select: "));
            } catch (NumberFormatException e) {
                continue;
            }

            if (inputIndex == ADD) {
                createItem(this.input, this.tracker);
            } else if (inputIndex == SHOW) {
                showAllItems(this.input, this.tracker);
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
     * @param input - Input
     * @param tracker - Tracker
     */
    private void showAllItems(Input input, Tracker tracker) {
        Item[] findItems = tracker.getAll();

        for (Item item: findItems) {
            System.out.println("id: " + item.getId()
                    + ", name: " + item.getName()
                    + ", description: " + item.getDesc()
                    + ", created: " + new Date(item.getCreated()));
        }

        input.ask("Press any key to return to the menu");
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

        input.ask("Press any key to return to the menu");
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

        input.ask("Press any key to return to the menu");
    }

    /**
     * main - точка входа.
     * @param args - String[]
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker(), new Menu()).init();
    }
}