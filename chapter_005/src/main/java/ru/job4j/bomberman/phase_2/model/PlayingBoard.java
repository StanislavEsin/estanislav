package ru.job4j.bomberman.phase_2.model;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * PlayingBoard.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public class PlayingBoard {
    /**
     */
    private final ReentrantLock[][] board;
    /**
     */
    private final ConcurrentHashMap<Coordinate, ObjectBoard> objects = new ConcurrentHashMap<>();

    /**
     * Constructor.
     * @param area - размер игрового поля.
     */
    public PlayingBoard(Coordinate area) {
        this.board = new ReentrantLock[area.getY()][area.getX()];
        initialization(area);
    }

    /**
     * initialization.
     * @param area - размер игрового поля.
     */
    private void initialization(Coordinate area) {
        int maxY = area.getY();
        for (int i = 0; i < maxY; i++) {

            int maxX = area.getX();
            for (int j = 0; j < maxX; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * getCell.
     * @param coordinate - Coordinate.
     * @return ReentrantLock.
     */
    public ReentrantLock getCell(Coordinate coordinate) {
        return this.board[coordinate.getY()][coordinate.getX()];
    }

    /**
     * getCoordinate.
     * @param cell - ReentrantLock.
     * @return Coordinate.
     */
    public Coordinate getCoordinate(ReentrantLock cell) {
        Coordinate result = null;

        for (int i = 0; i < this.board.length && result == null; i++) {
            for (int j = 0; j < this.board[i].length && result == null; j++) {
                if (cell.equals(this.board[i][j])) {
                    result = new Coordinate(i, j);
                }
            }
        }

        return result;
    }

    /**
     * addObject.
     * @param object - объект.
     * @param coordinate - координата.
     * @return boolean.
     */
    public boolean addObject(ObjectBoard object, Coordinate coordinate) {
        boolean result = false;

        ReentrantLock cell = this.getCell(coordinate);

        if (cell != null && cell.tryLock()) {
            this.objects.put(coordinate, object);
            result = true;
        }

        return result;
    }

    /**
     * removeObject.
     * @param object - объект.
     * @param coordinate - координата.
     * @return boolean.
     */
    public boolean removeObject(ObjectBoard object, Coordinate coordinate) {
        boolean result = false;

        ReentrantLock cell = this.getCell(coordinate);

        if (cell != null) {
            cell.unlock();
            this.objects.remove(coordinate);
            result = true;
        }

        return result;
    }

    /**
     * moveObject.
     * @param object - объект.
     * @param currentCoordinate - текущая координата.
     * @param toCoordinate - координата куда перемещаем объект.
     * @return boolean.
     */
    public boolean moveObject(ObjectBoard object, Coordinate currentCoordinate, Coordinate toCoordinate) {
        boolean result = false;

        ReentrantLock currentCell = this.getCell(currentCoordinate);
        ReentrantLock toCell = this.getCell(toCoordinate);

        if (currentCell != null && toCell != null && toCell.tryLock()) {
            currentCell.unlock();
            object.setCurrentCoordinate(toCoordinate);
            this.objects.remove(currentCoordinate);
            this.objects.put(toCoordinate, object);
            result = true;
        }

        return result;
    }

    /**
     * moveObject.
     * @param object - объект.
     * @param currentCoordinate - текущая координата.
     * @param toCoordinate - координата куда перемещаем объект.
     * @param timeout - время в милиссекундах за которое пытаемся переместить объект.
     * @return boolean.
     * @exception InterruptedException - за время которое пытаемся переместить объект, могут прервать.
     */
    public boolean moveObject(ObjectBoard object, Coordinate currentCoordinate, Coordinate toCoordinate,
                              int timeout) throws InterruptedException {
        boolean result = false;

        ReentrantLock currentCell = this.getCell(currentCoordinate);
        ReentrantLock toCell = this.getCell(toCoordinate);

        if (currentCell != null && toCell != null && toCell.tryLock(timeout, TimeUnit.MILLISECONDS)) {
            currentCell.unlock();
            object.setCurrentCoordinate(toCoordinate);
            this.objects.remove(currentCoordinate);
            this.objects.put(toCoordinate, object);
            result = true;
        }

        return result;
    }

    /**
     * checkValidityCoordinate.
     * @param coordinate - координата которую надо проверить.
     * @return boolean.
     */
    public boolean checkValidityCoordinate(Coordinate coordinate) {
        int y = coordinate.getY();
        int x = coordinate.getX();

        return y >= 0 && x >= 0 && y <= this.board.length - 1 && x <= this.board[y].length - 1;
    }

    /**
     * getObject.
     * @param coordinate - координата
     * @return ObjectBoard.
     */
    public ObjectBoard getObject(Coordinate coordinate) {
        return this.objects.get(coordinate);
    }

    /**
     * getMaximumY.
     * @return int.
     */
    public int getMaximumY() {
        return this.board.length;
    }

    /**
     * getCoordinate.
     * @param y - int.
     * @return int.
     */
    public int getMaximumX(int y) {
        return this.board[y].length;
    }
}