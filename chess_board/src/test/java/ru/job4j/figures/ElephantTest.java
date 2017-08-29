package ru.job4j.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.job4j.board.Cell;
import ru.job4j.exception.ImpossibleMoveException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ElephantTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     * @throws ImpossibleMoveException - .
     */
    @Test
    public void whenElephantWayOnOtherCellThenReturnArrayPath() throws ImpossibleMoveException {
        Elephant elephant = new Elephant(new Cell(4, 3));
        Cell otherCell = new Cell(2, 4);

        Cell[] result = elephant.way(otherCell);

        Cell[] expected = {otherCell};
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     * @throws ImpossibleMoveException - .
     */
    @Test
    public void whenElephantWayOnOtherCellThenImpossibleMoveException() throws ImpossibleMoveException {
        Elephant elephant = new Elephant(new Cell(4, 3));
        Cell otherCell = new Cell(3, 4);

        thrown.expect(ImpossibleMoveException.class);

        Cell[] result = elephant.way(otherCell);
    }

    /**
     * Test add.
     * @throws ImpossibleMoveException - .
     */
    @Test
    public void whenCloneFigureThenReturnNewFigureSameCell() throws ImpossibleMoveException {
        Figure elephant = new Elephant(new Cell(3, 5));
        Cell dist = new Cell(4, 8);

        Figure result = elephant.clone(dist);

        assertThat(result.position, is(dist));
    }
}