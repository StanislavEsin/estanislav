package ru.job4j.bomberman.phase_2.model;

import ru.job4j.bomberman.phase_2.controller.IStaticDestructibleObject;
import ru.job4j.bomberman.phase_2.exception.InitializationException;

/**
 * Wall - стена.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 08.11.2017
 */
public class Wall extends ObjectBoard implements IStaticDestructibleObject {
    /**
     */
    private final boolean[] destroyed = {false};

    /**
     * Constructor.
     */
    public Wall(PlayingBoard board, Coordinate startCoordinate) {
        super(ObjectType.WALL, board, startCoordinate);
    }

    @Override
    public void run() {
        initialization();

        synchronized (this) {
            while (!Thread.currentThread().isInterrupted() && !this.destroyed[0]) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    this.getBoard().removeObject(this, this.getCurrentCoordinate());
                }
            }

            this.getBoard().removeObject(this, this.getCurrentCoordinate());
        }
    }

    @Override
    public void blowUp() {
        synchronized (this) {
            this.destroyed[0] = true;
            notify();
        }
    }
}