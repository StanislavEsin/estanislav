package ru.job4j.vacancy;

import ru.job4j.vacancy.dao.PostgresFactory;
import ru.job4j.vacancy.model.Vacancy;
import ru.job4j.vacancy.utils.JobHandler;
import ru.job4j.vacancy.utils.PropertiesHolder;
import ru.job4j.vacancy.utils.parsers.JobParser;
import ru.job4j.vacancy.utils.parsers.JobParserSqlRu;
import ru.job4j.vacancy.utils.parsers.ParserTask;

import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Main.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 28.12.2017
 */
public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        LinkedBlockingQueue<Vacancy> queueVacancy = new LinkedBlockingQueue<>();

        JobHandler jobHandler = new JobHandler(queueVacancy, PostgresFactory.getInstance().getVacancyDAO());
        new Thread(jobHandler).start();

        JobParser jobParser = new JobParserSqlRu();
        ParserTask parserTask = new ParserTask(jobParser, queueVacancy, PostgresFactory.getInstance().getVacancyDAO());
        Timer timer = new Timer();
        timer.schedule(parserTask, 1, PropertiesHolder.getInstance().getRunTimeSchedule());
    }
}