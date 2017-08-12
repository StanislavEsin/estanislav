package ru.job4j.loop;

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
public class BoardTest {
    /**
     * Test add.
     */
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();

        String result = board.paint(3, 3);
        String line = "\r\n";
        String expected = String.format("x x%s x %sx x%s", line, line, line);

        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();

        String result = board.paint(5, 4);
        String line = "\r\n";
        String expected = String.format("x x x%s x x %sx x x%s x x %s", line, line, line, line);

        assertThat(result, is(expected));
    }
}

