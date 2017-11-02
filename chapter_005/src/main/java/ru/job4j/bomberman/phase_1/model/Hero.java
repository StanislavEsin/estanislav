package ru.job4j.bomberman.phase_1.model;

import ru.job4j.bomberman.phase_1.controller.Direction;
import ru.job4j.bomberman.phase_1.controller.IAIMovable;
import ru.job4j.bomberman.phase_1.controller.IMovableOneStep;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

/**
 * Hero.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public class Hero extends ObjectBoard implements IMovableOneStep, IAIMovable {
    /**
     * board - игровое поле.
     */
    private final PlayingBoard board;

    /**
     * Constructor.
     * @param board - игровое поле.
     * @param startCoordinate - начальные координаты.
     */
    public Hero(PlayingBoard board, Coordinate startCoordinate) {
        super(ObjectType.HERO, board.getCell(startCoordinate));
        this.board = board;
    }

    /**
     * makeStep.
     * @param currentCell - текущее поле.
     * @param newCoordinateY - новые координаты по оси Y.
     * @param newCoordinateX - новые координаты по оси X.
     * @return boolean.
     */
    private boolean makeStep(ReentrantLock currentCell, int newCoordinateY, int newCoordinateX) {
        boolean result = false;

        Coordinate lNewCoordinate = new Coordinate(newCoordinateY, newCoordinateX);
        ReentrantLock lNewCell = this.board.getCell(lNewCoordinate);

        try {
            if (lNewCell.tryLock(500, TimeUnit.MILLISECONDS)) {
                currentCell.unlock();
                setCurrentCell(lNewCell);
                result = true;
                System.out.println(String.format("%s походил из поля %s в поле %s"
                        , Thread.currentThread().getName(), this.board.getCoordinate(currentCell), lNewCoordinate));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void run() {
        initialization();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                move();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Hero interrupt.");
            }
        }
    }

    @Override
    public void initialization() {
        ReentrantLock currentCell = this.getCurrentCell();

        if (currentCell != null) {
            currentCell.lock();
        }
    }

    @Override
    public void move() {
        boolean moveDone = false;

        Random random = new Random();

        while (!moveDone) {
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

        ReentrantLock lCurrentCell = this.getCurrentCell();
        Coordinate lCurrentCoordinate = this.board.getCoordinate(lCurrentCell);

        if (lCurrentCoordinate.getY() - 1 >= 0) {
            result = makeStep(lCurrentCell, lCurrentCoordinate.getY() - 1, lCurrentCoordinate.getX());
        }

        return result;
    }

    @Override
    public boolean moveDown() {
        boolean result = false;

        ReentrantLock lCurrentCell = this.getCurrentCell();
        Coordinate lCurrentCoordinate = this.board.getCoordinate(lCurrentCell);

        if (lCurrentCoordinate.getY() + 1 <= this.board.getMaximumY() - 1) {
            result = makeStep(lCurrentCell, lCurrentCoordinate.getY() + 1, lCurrentCoordinate.getX());
        }

        return result;
    }

    @Override
    public boolean moveLeft() {
        boolean result = false;

        ReentrantLock lCurrentCell = this.getCurrentCell();
        Coordinate lCurrentCoordinate = this.board.getCoordinate(lCurrentCell);

        if (lCurrentCoordinate.getX() - 1 >= 0) {
            result = makeStep(lCurrentCell, lCurrentCoordinate.getY(), lCurrentCoordinate.getX() - 1);
        }

        return result;
    }

    @Override
    public boolean moveRight() {
        boolean result = false;

        ReentrantLock lCurrentCell = this.getCurrentCell();
        Coordinate lCurrentCoordinate = this.board.getCoordinate(lCurrentCell);

        if (lCurrentCoordinate.getX() + 1 <= this.board.getMaximumX(lCurrentCoordinate.getY()) - 1) {
            result = makeStep(lCurrentCell, lCurrentCoordinate.getY(), lCurrentCoordinate.getX() + 1);
        }

        return result;
    }
}