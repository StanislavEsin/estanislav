package ru.job4j.crudServlet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudServlet.dao.DAOException;
import ru.job4j.crudServlet.dao.UserStore;
import ru.job4j.crudServlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * UsersServlet.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.01.2018
 */
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (name == null || login == null || email == null) {
            result = "Not all parameters are filled in.";
        } else {
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            user.setEmail(email);
            user.setCreateDate(LocalDateTime.now());

            try {
                users.createUser(user);
                result = "User create.";
            } catch (DAOException e) {
                LOG.error("Cannot create user. ", e);
                result = "Cannot create user.";
            }
        }

        showMessage(result, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (name == null && login == null && email == null) {
            result = "None of the parameters is filled for update.";
        } else {

            try {
                int id = Integer.valueOf(req.getParameter("id"));
                User user = users.readUserById(id);

                if (user == null) {
                    result = "User with such ID does not exist.";
                } else {
                    if (name != null) {
                        user.setName(name);
                    }
                    if (login != null) {
                        user.setLogin(login);
                    }
                    if (email != null) {
                        user.setEmail(email);
                    }

                    users.updateUser(user);
                    result = "User update.";
                }
            } catch (NumberFormatException e) {
                LOG.error("Id is not full. ", e);
                result = "id is not full.";
            } catch (DAOException e) {
                LOG.error("Cannot update user. ", e);
                result = "Cannot update user.";
            }
        }

        showMessage(result, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;

        try {
            int id = Integer.valueOf(req.getParameter("id"));
            User user = users.readUserById(id);
            result = user == null ? "User with such ID does not exist." : user.toString();
        } catch (NumberFormatException e) {
            LOG.error("Parameter is missing or not entered correctly. ", e);
            result = "Parameter is missing or not entered correctly.";
        } catch (DAOException e) {
            LOG.error("Cannot read user by ID. ", e);
            result = "Error in database.";
        }

        showMessage(result, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;

        try {
            int id = Integer.valueOf(req.getParameter("id"));
            boolean delete = users.deleteUserById(id);
            result = delete ? "User delete." : "There is no user in the database with such ID.";
        } catch (NumberFormatException e) {
            LOG.error("Parameter is missing or not entered correctly. ", e);
            result = "Parameter is missing or not entered correctly.";
        } catch (DAOException e) {
            LOG.error("Cannot delete user. ", e);
            result = "Cannot delete user.";
        }

        showMessage(result, resp);
    }

    private void showMessage(String message, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(message);
        writer.flush();
    }
}