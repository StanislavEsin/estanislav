package ru.job4j.dao.ram;

import ru.job4j.dao.DAOFactory;
import ru.job4j.dao.Order;

/**
 * RAMFactory.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 04.12.2017
 */
public class RAMFactory extends DAOFactory {
    @Override
    public Order getOrderDAO() {
        return new Tracker();
    }
}