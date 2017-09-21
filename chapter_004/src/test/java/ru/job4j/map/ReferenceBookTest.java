package ru.job4j.map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 20.09.2017
 */
public class ReferenceBookTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     */
    @Test
    public void whenAddValueThenShouldReturnTheSameValue() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");

        String result = referenceBook.get("key1");

        assertThat(result, is("value1"));
    }

    /**
     * Test add.
     * @throws NullPointerException - .
     */
    @Test
    public void whenAddValueKeyNullThenShouldReturnNullPointerException() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();

        thrown.expect(NullPointerException.class);

        referenceBook.insert(null, "value1");
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddValueWithCollisionThenShouldReturnFalse() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");

        boolean result = referenceBook.insert("key1", "value1");

        assertThat(result, is(false));
    }

    /**
     * Test add.
     * @throws NullPointerException - .
     */
    @Test
    public void whenGetByKeyNullThenShouldReturnNullPointerException() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();

        thrown.expect(NullPointerException.class);

        referenceBook.get(null);
    }

    /**
     * Test add.
     */
    @Test
    public void whenGetByNotEqualsKeyThenShouldReturnNull() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");

        String result = referenceBook.get("key2");

        assertThat(result, is(nullValue()));
    }

    /**
     * Test add.
     * @throws NullPointerException - .
     */
    @Test
    public void whenDeleteByKeyNullThenShouldReturnNullPointerException() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();

        thrown.expect(NullPointerException.class);

        referenceBook.delete(null);
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteByNotEqualsKeyThenShouldReturnFalse() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");

        boolean result = referenceBook.delete("key2");

        assertThat(result, is(false));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteThenGetShouldReturnNull() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");

        referenceBook.delete("key1");
        String result = referenceBook.get("key1");

        assertThat(result, is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenIteratorGetNextCallThenShouldPointerForward() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");
        referenceBook.insert("key2", "value2");
        referenceBook.insert("key3", "value3");
        Iterator<String> iterator = referenceBook.iterator();
        iterator.next();
        iterator.next();

        String result = iterator.next();

        assertThat(result, is("key3"));
    }

    /**
     * Test add.
     * @throws NoSuchElementException - .
     */
    @Test
    public void whenGetNextCallThenNoSuchElementException() {
        ReferenceBook<String, String> referenceBook = new ReferenceBook<>();
        referenceBook.insert("key1", "value1");
        Iterator<String> iterator = referenceBook.iterator();
        iterator.next();

        thrown.expect(NoSuchElementException.class);

        iterator.next();
    }
}