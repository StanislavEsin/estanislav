package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class PaintTest {
    /**
     * Test add.
     */
    @Test
    public void whenStrategySquareThenPrintSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.setStrategy(new Square());

        paint.draw();

        String expected = "++++\r\n++++\r\n++++\r\n++++\r\n";

        assertThat(out.toString(), is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenStrategyTriangleThenPrintTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.setStrategy(new Triangle());

        paint.draw();

        String expected = "   +   \r\n  +++  \r\n +++++ \r\n+++++++\r\n";

        assertThat(out.toString(), is(expected));
    }
}