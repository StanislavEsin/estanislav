package ru.job4j.view;

/**
 * Menu.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Menu {
    /**
     */
    private String[] menu;

    /**
     * @see
     */
    public Menu() {
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
    public void showMenu() {
        clearConsole();
        System.out.println("---------------------");
        for (String positionMenu: this.menu) {
            System.out.println(positionMenu);
        }
        System.out.println("---------------------");
    }

    /**
     * clearConsole.
     */
    private void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) { }
    }
}