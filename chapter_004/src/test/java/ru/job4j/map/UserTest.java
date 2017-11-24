package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 20.09.2017
 */
public class UserTest {
    /**
     * Test add.
     */
    @Test
    public void addTwoUsers() {
        User userA = new User("test", 1, Calendar.getInstance());
        User userB = new User("test", 1, Calendar.getInstance());

        Map<User, Object> map = new HashMap<>();
        map.put(userA, new Object());
        map.put(userB, new Object());

        System.out.println(map.size());
    }
}