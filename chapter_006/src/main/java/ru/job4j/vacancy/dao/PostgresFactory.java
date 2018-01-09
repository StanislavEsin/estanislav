package ru.job4j.vacancy.dao;


/**
 * PostgresFactory.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 04.12.2017
 */
public enum PostgresFactory {
    INSTANCE;

    public VacancyDAO getVacancyDAO() {
        return new VacancyDAOImpl();
    }
}