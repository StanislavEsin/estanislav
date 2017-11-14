package ru.job4j.bomberman.phase_2.model;

import ru.job4j.bomberman.phase_2.exception.InitializationException;

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
    private final PlayingBoard board;
    /**
     */
    private final Coordinate[] currentCoordinate = new Coordinate[1];

    /**
     * Constructor.
     * @param type - тип объекта.
     * @param board - игровое поле.
     */
    public ObjectBoard(ObjectType type, PlayingBoard board, Coordinate startCoordinate) {
        this.type = type;
        this.board = board;
        this.currentCoordinate[0] = startCoordinate;
    }

    /**
     * getType.
     * @return ObjectType.
     */
    public ObjectType getType() {
        return this.type;
    }

    /**
     * getBoard.
     * @return PlayingBoard.
     */
    public PlayingBoard getBoard() {
        return this.board;
    }

    /**
     * getCurrentCoordinate.
     * @return Coordinate.
     */
    public Coordinate getCurrentCoordinate() {
        return this.currentCoordinate[0];
    }

    /**
     * setCurrentCoordinate.
     * @param coordinate - координата.
     */
    public void setCurrentCoordinate(Coordinate coordinate) {
        this.currentCoordinate[0] = coordinate;
    }

    /**
     * initialization.
     */
    protected void initialization() {
        if (!this.getBoard().addObject(this, this.getCurrentCoordinate())) {
            throw new InitializationException();
        }
    }
}