package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    /**
     * Test add.
     */
    @Test
    public void whenListUsersThenTreeSetSortedByAgeInAscendingOrder() {
        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User(1, "user1", "city1", 21));
        listUsers.add(new User(1, "user1", "city1", 15));
        listUsers.add(new User(1, "user1", "city1", 34));

        Set<User> result = new SortUser().sort(listUsers);

        Set<User> expected = new TreeSet<>();
        expected.add(new User(1, "user1", "city1", 15));
        expected.add(new User(1, "user1", "city1", 21));
        expected.add(new User(1, "user1", "city1", 34));

        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListUsersThenListUsersSortedByLengthOfName() {
        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User(1, "user123", "city1", 21));
        listUsers.add(new User(1, "user1", "city1", 15));
        listUsers.add(new User(1, "user12", "city1", 34));

        List<User> result = new SortUser().sortNameLength(listUsers);

        List<User> expected = new ArrayList<>();
        expected.add(new User(1, "user1", "city1", 15));
        expected.add(new User(1, "user12", "city1", 34));
        expected.add(new User(1, "user123", "city1", 21));

        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenListUsersThenListUsersSortedByTwoFieldsNameAndAge() {
        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User(1, "Сергей", "city1", 25));
        listUsers.add(new User(1, "Иван", "city1", 30));
        listUsers.add(new User(1, "Сергей", "city1", 20));
        listUsers.add(new User(1, "Иван", "city1", 25));

        List<User> result = new SortUser().sortByAllFields(listUsers);

        List<User> expected = new ArrayList<>();
        expected.add(new User(1, "Иван", "city1", 25));
        expected.add(new User(1, "Иван", "city1", 30));
        expected.add(new User(1, "Сергей", "city1", 20));
        expected.add(new User(1, "Сергей", "city1", 25));

        assertThat(result, is(expected));
    }
}