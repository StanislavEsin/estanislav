package ru.job4j.servlet.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.servlet.model.User;
import ru.job4j.servlet.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * UserStore.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 10.01.2018
 */
public class UserStore implements Repository<User, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);
    private static UserStore instance = new UserStore();
    private static DataSource dataSource;

    private UserStore() {
        dataSource = DataSource.INSTANCE;
        dataSource.init();
    }

    public static UserStore getInstance() {
        return instance;
    }

    @Override
    public void add(User entity) throws RepositoryException {
        try (Connection con = dataSource.getConnection()) {
            String sql = "INSERT INTO public.users (name, login, email, create_date) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, entity.getName());
            st.setString(2, entity.getLogin());
            st.setString(3, entity.getEmail());
            st.setTimestamp(4, Timestamp.valueOf(entity.getCreateDate()));

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    entity.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(Arrays.toString(e.getStackTrace()), e);
        }
    }

    @Override
    public User findByID(Integer id) throws RepositoryException {
        User result = null;

        try (Connection con = dataSource.getConnection()) {
            String sql = "Select * FROM public.users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                    result = user;
                } else {
                    throw new RepositoryException("User with such ID does not exist.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(Arrays.toString(e.getStackTrace()), e);
        }

        return result;
    }

    @Override
    public void update(User entity) throws RepositoryException {
        String name = entity.getName();
        String login = entity.getLogin();
        String email = entity.getEmail();
        LocalDateTime createDate = entity.getCreateDate();

        if (name == null && login == null && email == null && createDate == null) {
            throw new RepositoryException("All fields of the entity are empty, there is nothing to update.");
        }

        int id = entity.getId();

        if (id == 0) {
            throw new RepositoryException("Not filled ID of the entity.");
        }

        User user = findByID(id);

        if (user == null) {
            throw new RepositoryException("User with such ID does not exist.");
        }

        try (Connection con = dataSource.getConnection()) {
            String sql = "UPDATE public.users SET name = ?, login = ?, email = ?, create_date = ? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name != null ? name : user.getName());
            st.setString(2, login != null ? login : user.getLogin());
            st.setString(3, email != null ? email : user.getEmail());
            st.setTimestamp(4, createDate != null ? Timestamp.valueOf(createDate)
                    : Timestamp.valueOf(user.getCreateDate()));
            st.setInt(5, id);
            if (st.executeUpdate() == 0) {
                throw new RepositoryException("Failed to update the entity.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(Arrays.toString(e.getStackTrace()), e);
        }
    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        try (Connection con = dataSource.getConnection()) {
            String sql = "DELETE FROM public.users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            if (st.executeUpdate() == 0) {
                throw new RepositoryException("User with such ID does not exist.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(Arrays.toString(e.getStackTrace()), e);
        }
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        List<User> result = new LinkedList<>();

        try (Connection con = dataSource.getConnection()) {
            String sql = "Select * FROM public.users ORDER BY id";
            PreparedStatement st = con.prepareStatement(sql);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(Arrays.toString(e.getStackTrace()), e);
        }

        return result;
    }
}