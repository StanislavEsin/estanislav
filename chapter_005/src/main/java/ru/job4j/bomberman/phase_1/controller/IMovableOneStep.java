package ru.job4j.bomberman.phase_1.controller;

import ru.job4j.bomberman.phase_1.exception.CellBusyException;

/**
 * IMovableOneStep - возможность ходить на 1 клетку в разные стороны.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public interface IMovableOneStep {
    /**
     * moveUp.
     * @return boolean.
     */
    boolean moveUp() throws InterruptedException, CellBusyException;
    /**
     * moveRight.
     * @return boolean.
     */
    boolean moveRight();
    /**
     * moveDown.
     * @return boolean.
     */
    boolean moveDown();
    /**
     * moveLeft.
     * @return boolean.
     */
    boolean moveLeft();
}