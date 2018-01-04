package ru.job4j.vacancy.utils.parsers;

import java.util.concurrent.LinkedBlockingQueue;

public interface Parser<E> {
    void parse(LinkedBlockingQueue<E> container);
}