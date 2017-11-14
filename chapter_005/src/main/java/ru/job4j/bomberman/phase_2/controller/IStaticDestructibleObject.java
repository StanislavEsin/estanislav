package ru.job4j.bomberman.phase_2.controller;

/**
 * IStaticDestructibleObject - статический объект.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public interface IStaticDestructibleObject extends Runnable {
    /**
     * blowUp - взорвать-разрушить объект.
     */
    void blowUp();
}