package ru.job4j.generic;

/**
 * AbstractStore.
 * @param <T>
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.09.2017
 */
public class AbstractStore<T extends Base> implements Store<T> {
    /**
     */
    private SimpleArray<T> container;

    /**
     * Constructor.
     * @param size - int
     */
    public AbstractStore(int size) {
        this.container = new SimpleArray<>(size);
    }

    @Override
    public boolean add(T value) {
        return container.add(value);
    }

    @Override
    public boolean update(T value) {
        boolean result = false;
        Base foundObject = findById(value.getId());

        if (foundObject != null) {
            foundObject.setName(value.getName());
            result = true;
        }

        return result;
    }

    @Override
    public boolean delete(T value) {
        return container.delete(value);
    }

    @Override
    public Base findById(String id) {
        Base returnFound = null;

        for (int i = 0; i < container.size(); i++) {
            if (container.get(i).getId().equals(id)) {
                returnFound = container.get(i);
                break;
            }
        }

        return returnFound;
    }
}