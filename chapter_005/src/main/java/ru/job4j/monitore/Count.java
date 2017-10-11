package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 10.10.2017
 */
@ThreadSafe
public class Count implements Runnable {
    /**
     */
    @GuardedBy("Count.class") private static int count = 0;

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (Count.class) {
            System.out.println(incremant());
        }
    }

    /**
     * incremant.
     * @return - int.
     */
    private int incremant() {
        return ++Count.count;
    }
}