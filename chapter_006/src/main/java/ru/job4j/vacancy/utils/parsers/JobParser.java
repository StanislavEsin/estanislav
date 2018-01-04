package ru.job4j.vacancy.utils.parsers;

import ru.job4j.vacancy.model.Vacancy;
import ru.job4j.vacancy.utils.filters.FilterByDate;
import ru.job4j.vacancy.utils.filters.FilterByText;

/**
 * JobParser.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 03.01.2018
 */
public abstract class JobParser implements Parser<Vacancy> {
    protected final String url;
    protected FilterByDate filterByDate;
    protected FilterByText filterByText;

    protected JobParser(String url) {
        this.url = url;
    }

    public void setFilterByDate(FilterByDate filterByDate) {
        this.filterByDate = filterByDate;
    }

    public void setFilterByText(FilterByText filterByText) {
        this.filterByText = filterByText;
    }
}