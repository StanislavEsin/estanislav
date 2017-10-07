package ru.job4j.threads;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.10.2017
 */
public class WordCounterTest {
    /**
     * Test.
     */
    @Test
    public void whenMethodRunIsCalledThenPrintsHowManyWordsInThePassedParameter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        WordCounter wordCounter = new WordCounter("df    d  fs     gg  sdfsd ");

        wordCounter.run();

        assertThat(out.toString(), is("5" + System.lineSeparator()));
    }
}