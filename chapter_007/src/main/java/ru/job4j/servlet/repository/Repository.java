package ru.job4j.servlet.repository;

import java.util.List;

/**
 * Repository.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 10.01.2018
 */
public interface Repository<E, K> {
    void add(E entity) throws RepositoryException;
    E findByID(K id) throws RepositoryException;
    void update(E entity) throws RepositoryException;
    void delete(K id) throws RepositoryException;
    List<E> getAll() throws RepositoryException;
}