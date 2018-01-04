package ru.job4j.vacancy.dao;

import ru.job4j.vacancy.model.Vacancy;
import java.time.LocalDateTime;

public interface VacancyDAO {
    void add(Vacancy value) throws DAOException;
    LocalDateTime getLatestVacancyDate() throws DAOException;
}