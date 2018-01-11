package ru.job4j.servlet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.servlet.model.User;
import ru.job4j.servlet.repository.RepositoryException;
import ru.job4j.servlet.repository.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * AddUserServlet.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class AddUserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddUserServlet.class);
    private static final long serialVersionUID = 2771227506489679413L;
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setCreateDate(LocalDateTime.now());

        try {
            userStore.add(user);
        } catch (RepositoryException e) {
            LOG.error("Error adding user. ", e);
        }

        resp.sendRedirect("/");
    }
}