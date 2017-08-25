package ru.job4j.figures;

import ru.job4j.board.Cell;
import ru.job4j.exception.ImpossibleMoveException;

/**
 * Figure - шахматная фигура.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public abstract class Figure {
    /**
     */
    public final Cell POSITION;

    /**
     * @see
     * @param position - Cell.
     */
    public Figure(Cell position) {
        this.POSITION = position;
    }

    /**
     * clone - создаем новую фигуру.
     * @param dist - Cell - ячейка куда следует поставить новую фигуру.
     * @return Figure - возвращаем новую фигуру.
     */
    public abstract Figure clone(Cell dist);

    /**
     * way - задаем ячейку куда следует пойти. Если фигура может туда пойти, то Вернуть массив ячеек,
     * которые должна пройти фигура.
     * @throws ImpossibleMoveException - Если фигура туда пойти не может, выбросить исключение ImposibleMoveException.
     * @param dist - Cell - ячейка куда следует пойти.
     * @return Cell[] - массив ячеек. которые должна пройти фигура.
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}