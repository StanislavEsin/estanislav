package ru.job4j;

import java.util.Collections;
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

    /**
     * sort.
     * @param list - List<User>
     * @return return - List<User>
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, (user, t1) ->
                Integer.compare(user.getName().length(), t1.getName().length()));

        return list;
    }

    /**
     * sort.
     * @param list - List<User>
     * @return return - List<User>
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, (user, t1) -> {
            int compareName = user.getName().compareTo(t1.getName());
            return compareName != 0 ? compareName : Integer.compare(user.getAge(), t1.getAge());
        });

        return list;
    }
}