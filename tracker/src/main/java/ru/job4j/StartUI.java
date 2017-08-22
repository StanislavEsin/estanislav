package ru.job4j;

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
     * @see
     * @param input - Input
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * init.
     */
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();

        do {
            menu.show();
            int key = Integer.valueOf(this.input.ask("Select: "));
            menu.select(key);
        } while (!("y".equals(this.input.ask("Exit?(y): "))));
    }

    /**
     * main.
     * @param args - String[]
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }
}