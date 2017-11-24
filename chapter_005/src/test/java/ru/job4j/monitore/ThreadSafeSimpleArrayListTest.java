package ru.job4j.monitore;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 20.10.2017
 */
public class ThreadSafeSimpleArrayListTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        ThreadSafeSimpleArrayList<String> threadSaveArray = new ThreadSafeSimpleArrayList<>();
        threadSaveArray.add("test1");
        threadSaveArray.add("test2");

        String result = threadSaveArray.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        ThreadSafeSimpleArrayList<Integer> threadSaveArray = new ThreadSafeSimpleArrayList<>();
        threadSaveArray.add(2);
        threadSaveArray.add(5);
        threadSaveArray.add(8);

        Iterator<Integer> iterator = threadSaveArray.iterator();
        iterator.next();
        iterator.next();
        int result = iterator.next();

        assertThat(result, is(8));
    }

    /**
     * Test add.
     * @throws IndexOutOfBoundsException - .
     */
    @Test
    public void whenGetIndexOutOfBoundsThenIndexOutOfBoundsException() {
        ThreadSafeSimpleArrayList<String> threadSaveArray = new ThreadSafeSimpleArrayList<>();
        threadSaveArray.add("test1");

        thrown.expect(IndexOutOfBoundsException.class);

        threadSaveArray.get(1);
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenGetNextCallThenArrayIndexOutOfBoundsException() {
        ThreadSafeSimpleArrayList<Integer> threadSaveArray = new ThreadSafeSimpleArrayList<>();
        threadSaveArray.add(2);

        Iterator<Integer> iterator = threadSaveArray.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddValueInThreadThenThreadSafe() {
        ExecutorService service = Executors.newCachedThreadPool();
        ThreadSafeSimpleArrayList<Integer> threadSaveArray = new ThreadSafeSimpleArrayList<>();
        ThreadTest threadTest = new ThreadTest(threadSaveArray);
        for (int i = 0; i < 10; i++) {
            service.submit(new Thread(threadTest));
        }
        service.shutdown();
        while (!service.isTerminated()) {
            continue;
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            result += threadSaveArray.get(i);
        }

        assertThat(result, is(550));
    }

    /**
     * ThreadStorageTest.
     */
    public class ThreadTest implements Runnable {
        /**
         */
        private final ThreadSafeSimpleArrayList<Integer> array;

        /**
         * Constructor.
         * @param array - ThreadSafeSimpleArrayList<?>
         */
        public ThreadTest(final ThreadSafeSimpleArrayList<Integer> array) {
            this.array = array;
        }

        /**
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                this.array.add(i);
            }
        }
    }
}