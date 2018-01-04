package ru.job4j.vacancy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.vacancy.dao.DAOException;
import ru.job4j.vacancy.dao.VacancyDAO;
import ru.job4j.vacancy.model.Vacancy;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * JobHandler.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 28.12.2017
 */
public final class JobHandler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(JobHandler.class);
    private final LinkedBlockingQueue<Vacancy> container;
    private final VacancyDAO dao;

    public JobHandler(LinkedBlockingQueue<Vacancy> container, VacancyDAO dao) {
        this.container = container;
        this.dao = dao;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Vacancy vacancy = this.container.take();
                try {
                    this.dao.add(vacancy);
                } catch (DAOException e) {
                    LOG.error("Error add vacancy.", e);
                }
            } catch (InterruptedException e) {
                LOG.info("Job handler was interrupted.", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}