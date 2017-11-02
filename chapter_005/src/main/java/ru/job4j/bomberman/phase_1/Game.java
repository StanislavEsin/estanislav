package ru.job4j.bomberman.phase_1;

import ru.job4j.bomberman.phase_1.model.PlayingBoard;
import ru.job4j.bomberman.phase_1.model.Coordinate;
import ru.job4j.bomberman.phase_1.model.Hero;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Game.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public class Game  {
    /**
     */
    private final ExecutorService executors = Executors.newCachedThreadPool();

    /**
     * start.
     */
    public void start() {
        initialization();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }
    }

    /**
     * initialization.
     */
    private void initialization() {
        PlayingBoard board = new PlayingBoard(new Coordinate(10, 10));

        for (int i = 0; i < 2; i++) {
            Hero object = new Hero(board, new Coordinate(1 + i, 3));
            this.executors.submit(object);
        }
    }
}