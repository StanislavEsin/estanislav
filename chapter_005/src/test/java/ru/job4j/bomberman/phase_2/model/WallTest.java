package ru.job4j.bomberman.phase_2.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 09.11.2017
 */
public class WallTest {
    /**
     */
    private final PlayingBoard board = new PlayingBoard(new Coordinate(10, 10));
    /**
     */
    private final Coordinate coordinateWall = new Coordinate(2, 3);

    /**
     * Test - whenAddWallThenWallOnTheField.
     * @throws InterruptedException - InterruptedException.
     * */
    @Test
    public void whenAddWallThenWallOnTheField() throws InterruptedException {
        Thread wallThread = new Thread(new Wall(board, coordinateWall));
        wallThread.start();
        Thread.sleep(200);

        boolean result = board.getCell(coordinateWall).isLocked();

        assertThat(result, is(true));
    }

    /**
     * Test - whenBlowUpWallThenWallDonNotOnTheField.
     * @throws InterruptedException - InterruptedException.
     * */
    @Test
    public void whenBlowUpWallThenWallDonNotOnTheField() throws InterruptedException {
        Thread wallThread = new Thread(new Wall(board, coordinateWall));
        wallThread.start();
        Thread.sleep(200);

        Wall wall = (Wall) board.getObject(coordinateWall);
        wall.blowUp();
        Thread.sleep(200);
        boolean result = board.getCell(coordinateWall).isLocked();

        assertThat(result, is(false));
    }
}