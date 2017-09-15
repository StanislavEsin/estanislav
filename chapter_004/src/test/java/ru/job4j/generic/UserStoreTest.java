package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 15.09.2017
 */
public class UserStoreTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        UserStore<Base> userStore = new UserStore<>(3);
        userStore.add(new User("1", "user1"));
        userStore.add(new User("2", "user2"));
        userStore.add(new User("3", "user3"));

        Base result = userStore.findById("2");

        assertThat(result, is(new User("2", "user_test")));
    }

    /**
     * Test add.
     */
    @Test
    public void whenChangeNameThenUpdateInStore() {
        UserStore<Base> userStore = new UserStore<>(3);
        userStore.add(new User("1", "user1"));
        userStore.add(new User("2", "user2"));
        userStore.add(new User("3", "user3"));

        userStore.update(new User("2", "user_update"));
        String result = userStore.findById("2").getName();

        assertThat(result, is("user_update"));
    }
}