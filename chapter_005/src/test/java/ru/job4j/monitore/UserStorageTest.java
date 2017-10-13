package ru.job4j.monitore;

import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.10.2017
 */
public class UserStorageTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));

        User result = storage.getById(1);

        assertThat(result, is(new User(1, 100)));
    }

    /**
     * Test add.
     */
    @Test
    public void whenUpdateValueThenSameValueContainsUpdatedAmount() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.update(new User(1, 200));

        int result = storage.getById(1).getAmount();

        assertThat(result, is(200));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteThenGetShouldReturnNull() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));

        storage.delete(new User(1, 100));

        assertThat(storage.getById(1), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenTransferInThreadThenThreadSafe() {
        ExecutorService service = Executors.newCachedThreadPool();
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 500));
        storage.add(new User(2, 500));

        for (int i = 1; i <= 10; i++) {
            service.submit(new Thread(new ThreadStorageTest(storage, 1, 2)));
            service.submit(new Thread(new ThreadStorageTest(storage, 2, 1)));
        }
        try {
            Thread.sleep(1500);
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = storage.getById(1).getAmount() + storage.getById(2).getAmount();

        assertThat(result, is(1000));
    }

    /**
     * ThreadStorageTest.
     */
    public class ThreadStorageTest implements Runnable {
        /**
         */
        private final UserStorage storage;
        /**
         */
        private final int fromId;
        /**
         */
        private final int toId;

        /**
         * Constructor.
         * @param storage - UserStorage
         * @param fromId - int
         * @param toId - int
         */
        public ThreadStorageTest(final UserStorage storage, final int fromId, final int toId) {
            this.storage = storage;
            this.fromId = fromId;
            this.toId = toId;
        }

        /**
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                try {
                    this.storage.transfer(this.fromId, this.toId, 1);
                } catch (InsufficientSumException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}