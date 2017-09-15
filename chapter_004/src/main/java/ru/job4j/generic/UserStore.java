package ru.job4j.generic;

/**
 * UserStore.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class UserStore<T extends Base> extends AbstractStore<T> {
    /**
     * Constructor.
     * @param size - int
     */
    public UserStore(int size) {
        super(size);
    }
}