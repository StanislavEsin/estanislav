package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {
    /**
     * Test add.
     */
    @Test
    public void whenListUsersThenMapUsers() {
        List<User> listUsers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listUsers.add(new User(i, String.format("name_%s", i), String.format("city_%s", i), i));
        }

        HashMap<Integer, User> result = new UserConvert().process(listUsers);

        HashMap<Integer, User> expected = new HashMap<>();
        for (User value : listUsers) {
            expected.put(value.getId(), value);
        }
        assertThat(result, is(expected));
    }
}