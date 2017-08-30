package ru.job4j;

import java.util.HashMap;
import java.util.List;

/**
 * UserConvert.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * process.
     * @param list - List<User>
     * @return return - HashMap<Integer, User>
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> returnMap = new HashMap<>();

        for (User value : list) {
            returnMap.put(value.getId(), value);
        }

        return returnMap;
    }
}