package ru.job4j.ui;

import ru.job4j.dao.DAOFactory;
import ru.job4j.dao.Order;
import ru.job4j.dao.postgres.PostgresFactory;
import ru.job4j.dao.DAOException;

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
    private Order storage;

    /**
     * StartUI.
     * @param input - Input
     */
    public StartUI(Input input, Order storage) {
        this.input = input;
        this.storage = storage;
    }

    /**
     * init.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(input, this.storage);
        menu.fillActions();

        do {
            menu.show();
            menu.select(this.input.ask("Select: ", menu.getRanges()));
        } while (!("y".equals(this.input.ask("Exit?(y): "))));
    }

    /**
     * main.
     * @param args - String[]
     */
    public static void main(String[] args) throws DAOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        new StartUI(new ConsoleInput(), factory.getOrderDAO()).init();
        PostgresFactory.getInstance().closeAllConnection();
    }
}