package ru.job4j.generic;

/**
 * RoleStore.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {
    /**
     * Constructor.
     * @param size - int
     */
    public RoleStore(int size) {
        super(size);
    }
}