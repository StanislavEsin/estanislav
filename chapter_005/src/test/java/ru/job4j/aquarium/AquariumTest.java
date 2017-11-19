package ru.job4j.aquarium;

import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 18.11.2017
 */
public class AquariumTest {
    /**
     * Test.
     * */
    @Test
    public void demonstration() throws InterruptedException {
        Thread aquarium = new Thread(new Aquarium(2));
        aquarium.start();
        aquarium.join();
    }
}