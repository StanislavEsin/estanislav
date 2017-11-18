package ru.job4j.bomberman.phase_2.model;

import ru.job4j.bomberman.phase_2.controller.IMovableOneStep;

/**
 * Bomberman.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.11.2017
 */
public class Bomberman  extends ObjectBoard implements IMovableOneStep {
    /**
     */
    private final boolean[] destroyed = {false};

    /**
     * Constructor.
     */
    public Bomberman(PlayingBoard board, Coordinate startCoordinate) {
        super(ObjectType.BOMBERMAN, board, startCoordinate);
    }

    @Override
    public void run() {
        initialization();

        while (!Thread.currentThread().isInterrupted() && !this.destroyed[0]) {
            //обработка нажатия клавишь на клавиатуре
            continue;
        }

        if (this.destroyed[0]) {
            this.getBoard().removeObject(this, this.getCurrentCoordinate());
        }
    }

    /**
     * destroy.
     */
    public void destroy() {
        this.destroyed[0] = true;
    }

    @Override
    public boolean moveUp() {
        //логика хода на верх.
        return false;
    }

    @Override
    public boolean moveRight() {
        //логика хода на право.
        return false;
    }

    @Override
    public boolean moveDown() {
        //логика хода вниз.
        return false;
    }

    @Override
    public boolean moveLeft() {
        //логика хода на лево.
        return false;
    }
}