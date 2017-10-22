package ru.job4j.monitore;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 22.10.2017
 */
public class ThreadSafeLinkedListTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");
        threadSafeLinkedList.add("test2");

        String result = threadSafeLinkedList.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     * @throws IndexOutOfBoundsException - .
     */
    @Test
    public void whenGetIndexOutOfBoundsThenIndexOutOfBoundsException() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");

        thrown.expect(IndexOutOfBoundsException.class);

        threadSafeLinkedList.get(1);
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveFirstElementThenSecondElementBecomesTheFirst() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");
        threadSafeLinkedList.add("test2");
        threadSafeLinkedList.add("test3");

        threadSafeLinkedList.remove("test1");
        String result = threadSafeLinkedList.get(0);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveLastElementThenPenultimateElementBecomesTheLast() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");
        threadSafeLinkedList.add("test2");
        threadSafeLinkedList.add("test3");

        threadSafeLinkedList.remove("test3");
        String result = threadSafeLinkedList.get(1);

        assertThat(result, is("test2"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveElementInTheMiddleThenLinksAreRedefined() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");
        threadSafeLinkedList.add("test2");
        threadSafeLinkedList.add("test3");

        threadSafeLinkedList.remove("test2");
        String result = threadSafeLinkedList.get(1);

        assertThat(result, is("test3"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenRemoveElementWhichIsNotThenReturnFalse() {
        ThreadSafeLinkedList<String> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        threadSafeLinkedList.add("test1");
        threadSafeLinkedList.add("test2");
        threadSafeLinkedList.add("test3");

        boolean result = threadSafeLinkedList.remove("test4");

        assertThat(result, is(false));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddValueInThreadThenThreadSafe() {
        ExecutorService service = Executors.newCachedThreadPool();
        ThreadSafeLinkedList<Integer> threadSafeLinkedList = new ThreadSafeLinkedList<>();
        ThreadTest threadTest = new ThreadTest(threadSafeLinkedList);
        for (int i = 0; i < 10; i++) {
            service.submit(new Thread(threadTest));
        }
        service.shutdown();
        while (!service.isTerminated()) {}

        int result = 0;
        for (int i = 0; i < 100; i++) {
            result += threadSafeLinkedList.get(i);
        }

        assertThat(result, is(550));
    }

    /**
     * ThreadStorageTest.
     */
    public class ThreadTest implements Runnable {
        /**
         */
        private final ThreadSafeLinkedList<Integer> container;

        /**
         * Constructor.
         * @param container - ThreadSafeLinkedList<?>
         */
        public ThreadTest(final ThreadSafeLinkedList<Integer> container) {
            this.container = container;
        }

        /**
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                this.container.add(i);
            }

        }
    }
}