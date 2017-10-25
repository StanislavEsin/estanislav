package ru.job4j.wait_notify.call_center;

/**
 * ConsultantConsumer.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.10.2017
 */
public class ConsultantConsumer implements Runnable {
    /**
     */
    private ATE ate;

    /**
     * Constructor.
     * @param ate - ATE.
     */
    public ConsultantConsumer(ATE ate) {
        this.ate = ate;
    }

    /**
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.ate.waitCallOrPickUpPhone();
            } catch (InterruptedException e) {
                System.out.println("Consultants finished work.");
                Thread.currentThread().interrupt();
            }
        }
    }
}