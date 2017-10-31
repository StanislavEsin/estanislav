package ru.job4j.bomberman.phase_1.model;

import ru.job4j.bomberman.phase_1.controller.Direction;
import ru.job4j.bomberman.phase_1.controller.IAIMovable;
import ru.job4j.bomberman.phase_1.controller.IMovableOneStep;
import ru.job4j.bomberman.phase_1.exception.CellBusyException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * HERO.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public class Hero extends ObjectBoard implements IMovableOneStep, IAIMovable {
    /**
     */
    private final PlayingBoard board;

    private static final ReentrantLock lock = new ReentrantLock();

    public Hero(PlayingBoard board) {
        super(ObjectType.HERO);
        this.board = board;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                move();
                Thread.sleep(1000);
            } catch (CellBusyException e) {
                System.out.println("Game error..");
            } catch (InterruptedException e) {
                System.out.println("Hero interrupt.");
            }
        }
    }

    @Override
    public void move() throws InterruptedException, CellBusyException {
        boolean moveDone = false;

        for (int i = 0; i < 4 && !moveDone; i++) {
            Direction direction = Direction.values()[i];

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
    public boolean moveUp() throws InterruptedException, CellBusyException {
        boolean result = false;

        Coordinate currentCoordinate = this.board.getCoordinate(this);

        if (currentCoordinate != null) {
            int currentCoordinateY = currentCoordinate.getY();
            int currentCoordinateX = currentCoordinate.getX();

            if (currentCoordinateY - 1 >= 0) {
                ReentrantLock currentCell = this.board.getCell(currentCoordinate);

                Coordinate newCoordinate = new Coordinate(currentCoordinateY - 1, currentCoordinateX);
                ReentrantLock newCell = this.board.getCell(newCoordinate);

                System.out.println(currentCell.isLocked() + " - " + newCell.isLocked());

                if (newCell.tryLock(500, TimeUnit.MILLISECONDS)) {
                    System.out.println(currentCell.isLocked() + " - " + newCell.isLocked());
                    currentCell.unlock();

                    //после этого анлока не идет

                    this.board.changeCoordinate(currentCoordinate, newCoordinate, this);
                    result = true;
                    System.out.println("new coordinate: " + newCoordinate);
                }
            }
        }

        return result;
    }

    @Override
    public boolean moveRight() {
        boolean result = false;

        System.out.println("RIGHT");

        return result;
    }

    @Override
    public boolean moveDown() {
        boolean result = false;

        System.out.println("DOWN");

        return result;
    }

    @Override
    public boolean moveLeft() {
        boolean result = false;

        System.out.println("LEFT");

        return result;
    }
}