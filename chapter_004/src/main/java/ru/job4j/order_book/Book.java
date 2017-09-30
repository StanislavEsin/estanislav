package ru.job4j.order_book;

import java.util.Map;

/**
 * Book.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.09.2017
 */
public class Book {
    private String name;
    private Map orders;

    public Book(String name, Map orders) {
        this.name = name;
        this.orders = orders;
    }
}