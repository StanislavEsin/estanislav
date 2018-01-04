package ru.job4j.vacancy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacancy.model.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * VacancyDAOImpl.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 02.01.2018
 */
public final class VacancyDAOImpl implements VacancyDAO {
    private static final Logger LOG = LoggerFactory.getLogger(VacancyDAOImpl.class);
    private DataSource dataSource = DataSource.getInstance();

    @Override
    public void add(Vacancy value) throws DAOException {
        try (Connection con = this.dataSource.getConnection()) {
            String sql = "INSERT INTO public.vacancies (name, text, link, time_create) VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING RETURNING id";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, value.getName());
            st.setString(2, value.getText());
            st.setString(3, value.getLink());
            st.setTimestamp(4, Timestamp.valueOf(value.getTimeCreate()));

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    value.setId(rs.getLong("id"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can't add vacancy.", e);
        }
    }

    @Override
    public LocalDateTime getLatestVacancyDate() throws DAOException {
        LocalDateTime result = null;

        try (Connection con = this.dataSource.getConnection();
             Statement st = con.createStatement()) {

            String sql = "SELECT time_create FROM public.vacancies ORDER BY time_create DESC LIMIT 1";

            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    result = rs.getTimestamp("time_create").toLocalDateTime();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}