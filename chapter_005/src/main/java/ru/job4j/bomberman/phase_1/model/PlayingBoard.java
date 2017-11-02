package ru.job4j.bomberman.phase_1.model;

import java.util.concurrent.locks.ReentrantLock;

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