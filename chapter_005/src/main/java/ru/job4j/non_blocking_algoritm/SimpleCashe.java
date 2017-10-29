package ru.job4j.non_blocking_algoritm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * SimpleCashe.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 27.10.2017
 */
public class SimpleCashe<K extends Model> {
    /**
     */
    private final ConcurrentMap<Integer, K> cashe = new ConcurrentHashMap<>();

    /**
     * add.
     * @param value - K.
     * @return K.
     */
    public K add(K value) {
        return cashe.putIfAbsent(value.getId(), value);
    }

    /**
     * add.
     * @param value - K.
     * @throws OptimisticException - OptimisticException.
     */
    public void update(K value) throws OptimisticException {
        cashe.computeIfPresent(value.getId(), (integer, k) -> {
            if (k.getVersion() == value.getVersion()) {
                value.incrementVersion();
                return value;
            } else {
                throw new OptimisticException();
            }
        });
    }

    /**
     * delete.
     * @param id - int.
     * @return K.
     */
    public K delete(int id) {
        return cashe.remove(id);
    }
}