package ru.job4j.servlet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.servlet.model.User;
import ru.job4j.servlet.repository.RepositoryException;
import ru.job4j.servlet.repository.UserStore;
import ru.job4j.servlet.utils.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * UsersController.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 12.01.2018
 */
public class UsersController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);
    private static final long serialVersionUID = 2867607775533880545L;
    private UserStore userStore = UserStore.getInstance();

    @Override
    public void init() throws ServletException {
        DataSource.INSTANCE.init();
    }

    @Override
    public void destroy() {
        DataSource.INSTANCE.closePoolConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUsers = new LinkedList<>();
        try {
            listUsers = userStore.getAll();
        } catch (RepositoryException e) {
            LOG.error("An error occurred while retrieving the list of all users. ", e);
        }

        req.setAttribute("users", listUsers);
        req.setAttribute("sizeUsers", listUsers.size());
        req.getRequestDispatcher("/WEB-INF/views/UsersViews.jsp").forward(req, resp);
    }
}