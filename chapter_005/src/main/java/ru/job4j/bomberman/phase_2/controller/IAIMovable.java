package ru.job4j.bomberman.phase_2.controller;

/**
 * IAIMovable - "ИИ" движение по полю.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 31.10.2017
 */
public interface IAIMovable extends Runnable {
    /**
     * move - логика движения.
     */
    void move();
}