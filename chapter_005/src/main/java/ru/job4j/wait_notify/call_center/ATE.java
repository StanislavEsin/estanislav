package ru.job4j.wait_notify.call_center;

/**
 * ATE - Automatic telephone exchange.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.10.2017
 */
public class ATE {
    /**
     */
    private int numberWaitingConsultants = 0;
    /**
     */
    private int numberCallers = 0;

    /**
     * call.
     */
    public synchronized void call() throws InterruptedException {
        if (this.numberWaitingConsultants == 0) {
            this.numberCallers++;
            System.out.println("Calling wait when consultant picks up the phone.");
            wait();
        } else {
            Thread.sleep(500);

            this.numberWaitingConsultants--;
            System.out.println("Consultant picks up the phone.");
            notify();
        }
    }

    /**
     * waitCallOrPickUpPhone.
     */
    public synchronized void waitCallOrPickUpPhone() throws InterruptedException {
        if (this.numberCallers == 0) {
            this.numberWaitingConsultants++;
            System.out.println("Consultant wait call.");
            wait();
        } else {
            Thread.sleep(500);

            this.numberCallers--;
            System.out.println("Consultant picks up the phone.");
            notify();
        }
    }
}