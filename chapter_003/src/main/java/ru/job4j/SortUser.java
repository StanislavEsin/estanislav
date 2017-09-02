package ru.job4j;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUser.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * sort.
     * @param list - List<User>
     * @return return - Set<User>
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<User>(list);
    }
}