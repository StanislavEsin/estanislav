package ru.job4j.vacancy.dao;


/**
 * PostgresFactory.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 04.12.2017
 */
public final class PostgresFactory {
    private volatile static PostgresFactory instance;

    private PostgresFactory() {
    }

    public static PostgresFactory getInstance() {
        if (instance == null) {
            synchronized (PostgresFactory.class) {
                if (instance == null) {
                    instance = new PostgresFactory();
                }
            }
        }

        return instance;
    }

    public VacancyDAO getVacancyDAO() {
        return new VacancyDAOImpl();
    }
}