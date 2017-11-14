package ru.job4j.bomberman.phase_2;

import ru.job4j.bomberman.phase_2.model.Coordinate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Game.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 14.11.2017
 */
public class Game {
    /**
     */
    private final ExecutorService executors = Executors.newCachedThreadPool();
    /**
     */
    private final Coordinate area;
    /**
     */
    private final int numberMonsters;
    /**
     */
    private Thread bomberman;

    /**
     * Constructor.
     */
    public Game(Coordinate area, int numberMonsters) {
        this.area = area;
        this.numberMonsters = numberMonsters;
    }

    /**
     * start.
     */
    public void start() {
        initialization();

        while (!this.bomberman.isInterrupted()) {
            System.out.println("game");
        }

        System.out.println("The end!");
    }


    /**
     * initialization.
     */
    private void initialization() {
        //логика построения-загрузки игрового поля.

        //        int maxSize = this.area.getY() * this.area.getX();
//
//        if (this.numberMonsters > maxSize - 1) {
//            throw new InitializationException();
//        }
//
//        PlayingBoard board = new PlayingBoard(this.area);
//
//        for (int i = 1; i <= this.numberMonsters; i++) {
//            this.executors.submit(new Monster(board, new Coordinate(i, i)));
//        }

    }
}