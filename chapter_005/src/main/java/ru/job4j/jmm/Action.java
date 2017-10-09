package ru.job4j.jmm;

/**
 * Action.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 09.10.2017
 */
public class Action implements Runnable {
    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Count.count++;
            System.out.println(Count.count);
        }
    }
}