package ru.job4j.figures;

import ru.job4j.board.Cell;
import ru.job4j.exception.ImpossibleMoveException;

/**
 * Elephant - фигура слон.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Elephant extends Figure {
    /**
     * @param position - Cell.
     * @see
     */
    public Elephant(Cell position) {
        super(position);
    }

    /**
     * clone - создаем новую фигуру.
     * @param dist - Cell - ячейка куда следует поставить новую фигуру.
     * @return Figure - возвращаем новую фигуру.
     */
    @Override
    public Figure clone(Cell dist) {
        return new Elephant(dist);
    }

    /**
     * way - задаем ячейку куда следует пойти. Если фигура может туда пойти, то Вернуть массив ячеек,
     * которые должна пройти фигура.
     *
     * @param dist - Cell - ячейка куда следует пойти.
     * @return Cell[] - массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException - Если фигура туда пойти не может, выбросить исключение ImposibleMoveException.
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        int sourcePositionX = this.position.getPositionX();
        int sourcePositionY = this.position.getPositionY();

        int dx = Math.abs(dist.getPositionX() - sourcePositionX);
        int dy = Math.abs(dist.getPositionY() - sourcePositionY);


        if (dx >= 1 && dy >= 1 && dx + dy == 3) {
            return new Cell[] {dist};
        }

        throw new ImpossibleMoveException("Imposible move");
    }
}