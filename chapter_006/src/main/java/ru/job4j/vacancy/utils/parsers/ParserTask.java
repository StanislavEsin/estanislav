package ru.job4j.vacancy.utils.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacancy.dao.DAOException;
import ru.job4j.vacancy.dao.VacancyDAO;
import ru.job4j.vacancy.model.Vacancy;
import ru.job4j.vacancy.utils.filters.FilterByDate;
import ru.job4j.vacancy.utils.filters.FilterByText;

import java.time.LocalDateTime;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ParserTask.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 28.12.2017
 */
public final class ParserTask extends TimerTask {
    private static final Logger LOG = LoggerFactory.getLogger(ParserTask.class);
    private final JobParser parser;
    private final LinkedBlockingQueue<Vacancy> container;
    private final VacancyDAO dao;

    public ParserTask(JobParser parser, LinkedBlockingQueue<Vacancy> container, VacancyDAO dao) {
        this.parser = parser;
        this.container = container;
        this.dao = dao;
    }

    @Override
    public void run() {
        FilterByText filterByText = new FilterByText();
        filterByText.addWord("java", "JavaScript", "Java Script");

        try {
            LocalDateTime latestVacancyDate = this.dao.getLatestVacancyDate();

            if (latestVacancyDate == null) {
                latestVacancyDate = LocalDateTime.now().minusYears(1);
            }

            FilterByDate filterByDate = new FilterByDate(latestVacancyDate);
            this.parser.setFilterByText(filterByText);
            this.parser.setFilterByDate(filterByDate);
            this.parser.parse(this.container);
        } catch (DAOException e) {
            LOG.info("Error getting the date of the last vacancy.", e);
        }
    }
}