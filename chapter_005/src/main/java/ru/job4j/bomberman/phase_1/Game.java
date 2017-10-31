package ru.job4j.bomberman.phase_1;

import ru.job4j.bomberman.phase_1.exception.CellBusyException;
import ru.job4j.bomberman.phase_1.model.Coordinate;
import ru.job4j.bomberman.phase_1.model.Hero;
import ru.job4j.bomberman.phase_1.model.ObjectType;
import ru.job4j.bomberman.phase_1.model.PlayingBoard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Game.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public class Game  {
    private final ExecutorService executors = Executors.newCachedThreadPool();

    public void start() {
        try {
            initialization();
        } catch (CellBusyException e) {
            System.out.println("Initialization game error.");
            Thread.currentThread().interrupt();
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }
    }

    private void initialization() throws CellBusyException {
        PlayingBoard board = new PlayingBoard(new Coordinate(10, 10));

//        for (int i = 0; i < 2; i++) {
            Hero objectBoard = new Hero(board);
            board.addObjectInBoard(new Coordinate(5, 5), objectBoard);

            if (objectBoard.getType() == ObjectType.HERO) {
                this.executors.submit(objectBoard);
            }
//        }
    }
}