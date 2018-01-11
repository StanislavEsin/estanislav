package ru.job4j.servlet.repository;

/**
 * RepositoryException.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 05.12.2017
 */
public class RepositoryException extends Exception {
    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}