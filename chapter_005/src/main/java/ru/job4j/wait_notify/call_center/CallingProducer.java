package ru.job4j.wait_notify.call_center;

/**
 * CallingProducer.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.10.2017
 */
public class CallingProducer implements Runnable {
    /**
     */
    private ATE phone;

    /**
     * Constructor.
     * @param phone - ATE.
     */
    public CallingProducer(ATE phone) {
        this.phone = phone;
    }

    /**
     */
    @Override
    public void run() {
        try {
            this.phone.call();
        } catch (InterruptedException e) {
            System.out.println("The call was interrupted.");
        }
    }
}