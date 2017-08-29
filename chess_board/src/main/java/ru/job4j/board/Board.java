package ru.job4j.board;

import ru.job4j.exception.FigureNotFoundException;
import ru.job4j.exception.ImpossibleMoveException;
import ru.job4j.exception.OccupiedWayException;
import ru.job4j.figures.Figure;

/**
 * Board - шахматная доска.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Board {
    /**
     */
    private Figure[] figures;

    /**
     * @see
     * @param figures - Figure[].
     */
    public Board(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * getFigures.
     * @return Figure[].
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * move - проверяет возможность перемещение фигуры.
     * @throws ImpossibleMoveException - если фигура не может так двигаться.
     * @throws OccupiedWayException - если полученный путь занят фигурами.
     * @throws FigureNotFoundException - в заданной ячейки нет фигуры.
     * @param source - Cell - ячейка от куда следует пойти.
     * @param dist - Cell - ячейка куда следует пойти.
     * @return boolean.
     */
    public boolean move(Cell source, Cell dist) throws
            ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        Figure figureSource = null;

        int i;
        for (i = 0; i < this.figures.length; i++) {
            if (figures[i].position.equals(source)) {
                figureSource = figures[i];
                break;
            }
        }

        if (figureSource == null) {
            throw new FigureNotFoundException("In the cells of the source is no figures.");
        }

        Cell[] path = figureSource.way(dist);

        for (Cell cellPath : path) {
            for (Figure figure: figures) {
                if (cellPath.equals(figure.position)) {
                    throw new OccupiedWayException("Received from the path is busy figures");
                }
            }
        }

        figures[i] = figureSource.clone(dist);

        return true;
    }
}