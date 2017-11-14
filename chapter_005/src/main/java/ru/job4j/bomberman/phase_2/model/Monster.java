package ru.job4j.bomberman.phase_2.model;

import ru.job4j.bomberman.phase_2.controller.Direction;
import ru.job4j.bomberman.phase_2.controller.IAIMovable;
import ru.job4j.bomberman.phase_2.controller.IMovableOneStep;

import java.util.Random;

/**
 * Monster.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 09.11.2017
 */
public class Monster extends ObjectBoard implements IMovableOneStep, IAIMovable {
    /**
     * Constructor.
     */
    public Monster(PlayingBoard board, Coordinate startCoordinate) {
        super(ObjectType.MONSTER, board, startCoordinate);
    }

    @Override
    public void run() {
        initialization();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(this.getCurrentCoordinate());
                move();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void move() {
        boolean moveDone = false;

        Random random = new Random();

        while (!moveDone && !Thread.currentThread().isInterrupted()) {
            Direction direction = Direction.values()[random.nextInt(4)];

            switch (direction) {
                case UP:
                    moveDone = moveUp();
                    break;
                case RIGHT:
                    moveDone = moveRight();
                    break;
                case DOWN:
                    moveDone = moveDown();
                    break;
                case LEFT:
                    moveDone = moveLeft();
                    break;
            }
        }
    }

    @Override
    public boolean moveUp() {
        boolean result = false;

        Coordinate currentCoordinate = this.getCurrentCoordinate();
        Coordinate nextCoordinate = new Coordinate(currentCoordinate.getY() - 1, currentCoordinate.getX());

        result = makeStep(currentCoordinate, nextCoordinate);

        return result;
    }

    @Override
    public boolean moveDown() {
        boolean result = false;

        Coordinate currentCoordinate = this.getCurrentCoordinate();
        Coordinate nextCoordinate = new Coordinate(currentCoordinate.getY() + 1, currentCoordinate.getX());

        result = makeStep(currentCoordinate, nextCoordinate);

        return result;
    }

    @Override
    public boolean moveRight() {
        boolean result = false;

        Coordinate currentCoordinate = this.getCurrentCoordinate();
        Coordinate nextCoordinate = new Coordinate(currentCoordinate.getY(), currentCoordinate.getX() + 1);

        result = makeStep(currentCoordinate, nextCoordinate);

        return result;
    }

    @Override
    public boolean moveLeft() {
        boolean result = false;

        Coordinate currentCoordinate = this.getCurrentCoordinate();
        Coordinate nextCoordinate = new Coordinate(currentCoordinate.getY(), currentCoordinate.getX() - 1);

        result = makeStep(currentCoordinate, nextCoordinate);

        return result;
    }

    /**
     * makeStep - сделать шаг.
     * @param currentCoordinate - текущая координата.
     * @param nextCoordinate - след. координата.
     * @return boolean.
     */
    private boolean makeStep(Coordinate currentCoordinate, Coordinate nextCoordinate) {
        boolean result = false;

        PlayingBoard board = this.getBoard();

        if (board.checkValidityCoordinate(nextCoordinate)) {
            ObjectBoard objectOnNextCoordinate = board.getObject(nextCoordinate);

            if (objectOnNextCoordinate == null) {
                result = board.moveObject(this, currentCoordinate, nextCoordinate);
            } else {
                switch (objectOnNextCoordinate.getType()) {
                    case BOMBERMAN:
                        ((Bomberman)objectOnNextCoordinate).destroy();

                        try {
                            result = board.moveObject(this, currentCoordinate, nextCoordinate, 500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                    case MONSTER:
                        try {
                            result = board.moveObject(this, currentCoordinate, nextCoordinate, 500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                }
            }
        }

        return result;
    }
}