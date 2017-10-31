package ru.job4j.bomberman.phase_1.model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;

import ru.job4j.bomberman.phase_1.exception.CellBusyException;

/**
 * PlayingBoard.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 29.10.2017
 */
public class PlayingBoard {
    private final ReentrantLock[][] board;
    private final ConcurrentHashMap<Coordinate, ObjectBoard> objectsBoard = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public PlayingBoard(Coordinate area) {
        this.board = new ReentrantLock[area.getY()][area.getX()];
        initialization(area);
    }

    private void initialization(Coordinate area) {
        int maxY = area.getY();
        for (int i = 0; i < maxY; i++) {

            int maxX = area.getX();
            for (int j = 0; j < maxX; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public void addObjectInBoard(Coordinate coordinate, ObjectBoard object) throws CellBusyException {
        ReentrantLock cell = getCell(coordinate);

        if (cell.isLocked()) {
            throw new CellBusyException();
        }

        this.objectsBoard.put(coordinate, object);
        cell.lock();
    }

    public void changeCoordinate(Coordinate currentCoordinate, Coordinate newCoordinate
            , ObjectBoard value) throws CellBusyException {

        this.objectsBoard.remove(currentCoordinate);
        this.objectsBoard.put(newCoordinate, value);
    }


    public Coordinate getCoordinate(ObjectBoard object) {
        this.lock.lock();

        Coordinate result = null;

        Set<Map.Entry<Coordinate, ObjectBoard>> entrySet = this.objectsBoard.entrySet();
        for (Map.Entry<Coordinate, ObjectBoard> pair : entrySet) {
            if (object.equals(pair.getValue())) {
                result = pair.getKey();
                break;
            }
        }

        this.lock.unlock();

        return result;
    }

    public ReentrantLock getCell(Coordinate coordinate) {
        return this.board[coordinate.getY()][coordinate.getX()];
    }

    public int getMaximumY() {
        return this.board.length;
    }

    public int getMaximumX(int y) {
        return this.board[y].length;
    }
}