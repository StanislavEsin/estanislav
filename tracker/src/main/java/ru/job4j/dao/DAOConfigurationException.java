package ru.job4j.dao;

/**
 * DAOConfigurationException.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 05.12.2017
 */
public class DAOConfigurationException extends Exception {
    public DAOConfigurationException() {
        super();
    }

    public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}