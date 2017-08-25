package ru.job4j.board;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.job4j.figures.Elephant;
import ru.job4j.figures.Figure;
import ru.job4j.exception.FigureNotFoundException;
import ru.job4j.exception.ImpossibleMoveException;
import ru.job4j.exception.OccupiedWayException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test add.
     * @throws OccupiedWayException - .
     * @throws ImpossibleMoveException - .
     * @throws FigureNotFoundException - .
     */
    @Test
    public void whenMoveThenDeleteFigureSourceCellAndNewFigureDestinationCell()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Cell source = new Cell(4, 3);
        Cell dist = new Cell(2, 4);
        Figure[] figures = {new Elephant(source)};
        Board board = new Board(figures);

        board.move(source, dist);
        Cell result = board.getFigures()[0].POSITION;

        assertThat(result, is(dist));
    }

    /**
     * Test add.
     * @throws OccupiedWayException - .
     * @throws ImpossibleMoveException - .
     * @throws FigureNotFoundException - .
     */
    @Test
    public void whenMoveButImpossibleMoveThenImpossibleMoveException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Cell source = new Cell(4, 3);
        Cell dist = new Cell(3, 4);
        Figure[] figures = {new Elephant(source)};
        Board board = new Board(figures);

        thrown.expect(ImpossibleMoveException.class);

        board.move(source, dist);
    }

    /**
     * Test add.
     * @throws OccupiedWayException - .
     * @throws ImpossibleMoveException - .
     * @throws FigureNotFoundException - .
     */
    @Test
    public void whenMoveButOccupiedWayThenOccupiedWayException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Cell source = new Cell(4, 3);
        Cell dist = new Cell(2, 4);
        Figure[] figures = {new Elephant(source), new Elephant(new Cell(2, 4))};
        Board board = new Board(figures);

        thrown.expect(OccupiedWayException.class);

        board.move(source, dist);
    }

    /**
     * Test add.
     * @throws OccupiedWayException - .
     * @throws ImpossibleMoveException - .
     * @throws FigureNotFoundException - .
     */
    @Test
    public void whenMoveButFigureNotInCellThenFigureNotFoundException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Cell source = new Cell(4, 3);
        Cell dist = new Cell(2, 4);
        Figure[] figures = {new Elephant(source)};
        Board board = new Board(figures);

        thrown.expect(FigureNotFoundException.class);

        board.move(new Cell(5, 6), dist);
    }
}