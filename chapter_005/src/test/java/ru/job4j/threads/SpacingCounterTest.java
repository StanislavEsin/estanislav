package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class SpacingCounterTest {
    /**
     * Test.
     */
    @Test
    public void whenMethodRunIsCalledThenPrintsHowManySpacesInThePassedParameter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        SpacingCounter spacingCounter = new SpacingCounter("test  test    test");

        spacingCounter.run();

        assertThat(out.toString(), is("6" + System.lineSeparator()));
    }
}