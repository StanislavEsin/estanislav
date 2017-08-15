package ru.job4j.inheritance;

/**
 * Doctor - доктор.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {
    /**
     *  viewOfDoctor - вид доктора.
     */
    private ViewOfDoctor viewOfDoctor;

    /**
     * Constructor
     * @param name - String
     * @param viewOfDoctor - ViewOfDoctor
     */
    public Doctor(String name, ViewOfDoctor viewOfDoctor) {
        super(name);
    }

    /**
     * heal.
     * @param pacient - Pacient
     * @return return - String
     */
    public String heal(Pacient pacient) {
        return "Доктор " + this.getName() + " лечит " + pacient.getName();
    }
}