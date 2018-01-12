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

/**
 * UpdateUserController.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class UpdateUserController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateUserController.class);
    private static final long serialVersionUID = 6328444530140780881L;
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userStore.findByID(Integer.valueOf(req.getParameter("id")));

            if (user != null) {
                String name = req.getParameter("name");
                String login = req.getParameter("login");
                String email = req.getParameter("email");

                if (name != null && !name.trim().isEmpty()) {
                    user.setName(name);
                }
                if (login != null && !login.trim().isEmpty()) {
                    user.setLogin(login);
                }
                if (email != null && !email.trim().isEmpty()) {
                    user.setEmail(email);
                }

                userStore.update(user);
            }
        } catch (NumberFormatException  e) {
            LOG.error("Not the correct format id. ", e);
        } catch (RepositoryException e) {
            LOG.error("Error adding user. ", e);
        }

        resp.sendRedirect(req.getContextPath().length() == 0 ? "/" : req.getContextPath());
    }
}