package ru.job4j.wait_notify.call_center;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.10.2017
 */
public class ATETest {
    /**
     * Test.
     */
    @Test
    @Ignore("demonstration")
    public void demonstration() {
        ATE ate = new ATE();
        Thread threadConsultant1 = new Thread(new ConsultantConsumer(ate));
        Thread threadConsultant2 = new Thread(new ConsultantConsumer(ate));
        threadConsultant1.start();
        threadConsultant2.start();

        for (int i = 0; i < 10; i++) {
            new Thread(new CallingProducer(ate)).start();
        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadConsultant1.interrupt();
        threadConsultant2.interrupt();
    }
}