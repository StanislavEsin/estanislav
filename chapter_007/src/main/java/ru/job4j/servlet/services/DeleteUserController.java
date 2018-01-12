package ru.job4j.servlet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.servlet.repository.RepositoryException;
import ru.job4j.servlet.repository.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * DeleteUserController.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class DeleteUserController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteUserController.class);
    private static final long serialVersionUID = 3879293457880302897L;
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getContextPath());
        try {
            userStore.delete(Integer.valueOf(req.getParameter("id")));
        } catch (NumberFormatException e) {
            LOG.error("Not the correct format id. ", e);
        } catch (RepositoryException e) {
            LOG.error("Error while deleting user. ", e);
        }

        resp.sendRedirect(req.getContextPath().length() == 0 ? "/" : req.getContextPath());
    }
}