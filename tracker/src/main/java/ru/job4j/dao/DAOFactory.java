package ru.job4j.dao;

import ru.job4j.dao.postgres.PostgresFactory;
import ru.job4j.dao.ram.RAMFactory;

/**
 * DAOFactory.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 04.12.2017
 */
public abstract class DAOFactory {
    /**
     */
    public static final int RAM = 1;

    /**
     */
    public static final int POSTGRES = 2;

    /**
     * getDAOFactory.
     */
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case RAM:
                return new RAMFactory();
            case POSTGRES:
                return PostgresFactory.getInstance();
            default:
                return new RAMFactory();
        }
    }

    /**
     * getOrderDAO.
     */
    public abstract Order getOrderDAO();
}