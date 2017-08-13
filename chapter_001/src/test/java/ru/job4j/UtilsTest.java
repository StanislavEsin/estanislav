package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UtilsTest {
    /**
     * Test add.
     */
    @Test
    public void whenSubStringInStringThenTrue() {
        Utils utils = new Utils();

        String string = "Привет";
        String subString = "иве";
        boolean result = utils.contains(string, subString);

        assertThat(result, is(true));
    }
}