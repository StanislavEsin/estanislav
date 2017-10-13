package ru.job4j.monitore;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserStorage.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.10.2017
 */
@ThreadSafe
public class UserStorage {
    /**
     */
    private final ConcurrentHashMap<Integer, User> map;

    /**
     * Constructor.
     */
    public UserStorage() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * add.
     * @param user - User
     * @return User
     */
    public User add(User user) {
        return this.map.put(user.getId(), user);
    }

    /**
     * update.
     * @param user - User
     * @return User
     */
    public User update(User user) {
        return this.map.replace(user.getId(), user);
    }

    /**
     * delete.
     * @param user - User
     * @return boolean
     */
    public boolean delete(User user) {
        return this.map.remove(user.getId(), user);
    }

    /**
     * getById.
     * @param id - int
     * @return User
     */
    public User getById(int id) {
        return this.map.get(id);
    }

    /**
     * transfer.
     * @param fromId - int
     * @param toId - int
     * @param amount - int
     * @throws InsufficientSumException - Insufficient sum.
     */
    public void transfer(int fromId, int toId, int amount) throws InsufficientSumException {
        User fromUser = this.map.get(fromId);
        User toUser = this.map.get(toId);

        if (fromUser != null && toUser != null) {
            synchronized (fromId < toId ? fromUser : toUser) {
                synchronized (fromId > toId ? fromUser : toUser) {
                    if (fromUser.getAmount() >= amount) {
                        fromUser.setAmount(fromUser.getAmount() - amount);
                        toUser.setAmount(toUser.getAmount() + amount);
                    } else {
                        throw new InsufficientSumException();
                    }
                }
            }
        }
    }
}