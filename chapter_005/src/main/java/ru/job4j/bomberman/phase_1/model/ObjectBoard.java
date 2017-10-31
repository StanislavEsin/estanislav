package ru.job4j.bomberman.phase_1.model;

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

    public ObjectBoard(ObjectType type) {
        this.type = type;
    }

    public ObjectType getType() {
        return type;
    }
}