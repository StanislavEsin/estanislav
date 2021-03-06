package ru.job4j.vacancy.dao;

/**
 * DAOException.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 05.12.2017
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}