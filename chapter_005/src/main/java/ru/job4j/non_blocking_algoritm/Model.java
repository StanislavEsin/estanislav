package ru.job4j.non_blocking_algoritm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Model.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public abstract class Model {
    /**
     */
    private int id;
    /**
     */
    private final AtomicInteger version = new AtomicInteger(0);

    /**
     * Constructor.
     * @param id = int
     */
    public Model(int id) {
        this.id = id;
    }

    /**
     * incrementVersion.
     * @return int
     */
    public int incrementVersion() {
        return version.incrementAndGet();
    }

    /**
     * getId.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * getVersion.
     * @return int
     */
    public int getVersion() {
        return version.get();
    }
}