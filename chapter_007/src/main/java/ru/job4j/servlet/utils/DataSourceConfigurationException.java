package ru.job4j.servlet.utils;

/**
 * DataSourceConfigurationException.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class DataSourceConfigurationException extends RuntimeException {
    public DataSourceConfigurationException() {
        super();
    }

    public DataSourceConfigurationException(String message) {
        super(message);
    }

    public DataSourceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}