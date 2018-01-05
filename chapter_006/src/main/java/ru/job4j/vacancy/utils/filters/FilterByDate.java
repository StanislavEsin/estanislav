package ru.job4j.vacancy.utils.filters;

import java.time.LocalDateTime;

/**
 * FilterByDate.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.12.2017
 */
public final class FilterByDate {
    private final LocalDateTime date;

    public FilterByDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public boolean check(LocalDateTime value) {
        return this.date.isBefore(value);
    }

    @Override
    public String toString() {
        return "FilterByDate{" + "date=" + date + '}';
    }
}