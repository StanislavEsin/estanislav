package ru.job4j.board;

/**
 * Cell - ячейка шахматной доски.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Cell {
    /**
     */
    private int positionX;
    /**
     */
    private int positionY;

    /**
     * @see
     * @param positionX - int.
     * @param positionY - int.
     */
    public Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * getPositionX.
     * @return int.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * getPositionY.
     * @return int.
     */
    public int getPositionY() {
        return positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (positionX != cell.positionX) {
            return false;
        }
        return positionY == cell.positionY;
    }

    @Override
    public int hashCode() {
        int result = positionX;
        result = 31 * result + positionY;
        return result;
    }
}