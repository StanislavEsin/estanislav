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
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

/**
 * IndexServlet.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class IndexServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    private static final long serialVersionUID = -8118687925541618459L;
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUsers = new LinkedList<>();
        try {
            listUsers = userStore.getAll();
        } catch (RepositoryException e) {
            LOG.error("An error occurred while retrieving the list of all users. ", e);
        }

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Application for working with the user.</title>"
                + "</head>"
                + "<body>"
                + "<h1>Application for working with the user.</h1>"
                + "<form id='data'>"
                + "<p><b>ID:&nbsp</b><input name='id'></p>"
                + "<p><b>Name:&nbsp</b><input name='name'></p>"
                + "<p><b>Login:&nbsp</b><input name='login'></p>"
                + "<p><b>E-mail:&nbsp</b><input name='email'></p>"
                + "</form>"
                + "<button type='submit' form='data' formaction='" + req.getContextPath() + "/add' formmethod='post'>Add</button>"
                + "<button type='submit' form='data' formaction='" + req.getContextPath() + "/update' formmethod='post' >Update</button>"
                + "<button type='submit' form='data' formaction='" + req.getContextPath() + "/delete' formmethod='post' >Delete</button>"
                + "<p></p>"
                + "<b>List of users in the database</b>"
                + "<p></p>");

        if (listUsers.size() > 0) {
            writer.append("<table>"
                    + "<tr>"
                    + "<td>ID</td>"
                    + "<td>Login</td>"
                    + "<td>Name</td>"
                    + "<td>E-mail</td>"
                    + "<td>Data create</td>"
                    + "</tr>");

            for (User user : listUsers) {
                writer.append("<tr>"
                        + "<td>" + user.getId() + "</td>"
                        + "<td>" + user.getLogin() + "</td>"
                        + "<td>" + user.getName() + "</td>"
                        + "<td>" + user.getEmail() + "</td>"
                        + "<td>" + user.getCreateDate() + "</td>"
                        + "</tr>");
            }

            writer.append("</table>");
        }


        writer.append("</body></html>");
        writer.flush();
    }

    @Override
    public void init() throws ServletException {
        DataSource.INSTANCE.init();
    }

    @Override
    public void destroy() {
        DataSource.INSTANCE.closePoolConnection();
    }
}