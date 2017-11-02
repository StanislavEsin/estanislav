package ru.job4j.bomberman.phase_1.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ObjectBoard.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public abstract class ObjectBoard {
    /**
     */
    private final ObjectType type;
    /**
     */
    private final ReentrantLock[] currentCell = new ReentrantLock[1];

    /**
     * Constructor.
     * @param type - тип объекта.
     * @param startCell - начальные координаты.
     */
    public ObjectBoard(ObjectType type, ReentrantLock startCell) {
        this.type = type;
        this.currentCell[0] = startCell;
    }

    /**
     * getType.
     * @return ObjectType.
     */
    public ObjectType getType() {
        return this.type;
    }

    /**
     * getCurrentCell.
     * @return ReentrantLock.
     */
    public ReentrantLock getCurrentCell() {
        return this.currentCell[0];
    }

    /**
     * setCurrentCell.
     * @param cell - поле.
     */
    public void setCurrentCell(ReentrantLock cell) {
        this.currentCell[0] = cell;
    }

    /**
     * initialization.
     */
    public abstract void initialization();
}