package ru.job4j.controller;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StubInputTest {
    /**
     * Test add.
     */
    @Test
    public void whenFirstTimeEnterInConsoleThenReturnFirstValue() {
        String[] answers = {"0"};
        StubInput stubInput = new StubInput(answers);

        String result = stubInput.ask("test");

        assertThat(result, is("0"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenSecondTimeEnterInConsoleThenReturnSecondValue() {
        String[] answers = {"0", "name"};
        StubInput stubInput = new StubInput(answers);

        stubInput.ask("test");
        String result = stubInput.ask("test");

        assertThat(result, is("name"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenThirdTimeEnterInConsoleThenReturnThirdValue() {
        String[] answers = {"0", "name", "desc"};
        StubInput stubInput = new StubInput(answers);

        stubInput.ask("test");
        stubInput.ask("test");
        String result = stubInput.ask("test");

        assertThat(result, is("desc"));
    }
}