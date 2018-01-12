<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.servlet.model.User" %>
<%@ page import="ru.job4j.servlet.repository.RepositoryException" %>
<%@ page import="ru.job4j.servlet.repository.UserStore" %>
<%@ page import="ru.job4j.servlet.utils.DataSource" %>

<%!
    private static final Logger LOG = LoggerFactory.getLogger("JSPIndex");
    private UserStore userStore = UserStore.getInstance();

    public void jspInit() {
        DataSource.INSTANCE.init();
    }

    public void jspDestroy() {
        DataSource.INSTANCE.closePoolConnection();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <head>
        <title>Application for working with the user.</title>
    </head>
    <body>
        <h1>Application for working with the user.</h1>
        <form id="data">
            <p><b>ID:&nbsp</b><input name="id"></p>
            <p><b>Name:&nbsp</b><input name="name"></p>
            <p><b>Login:&nbsp</b><input name="login"></p>
            <p><b>E-mail:&nbsp</b><input name="email"></p>
        </form>
        <button type='submit' form='data' formaction="/add" formmethod="post">Add</button>
        <button type='submit' form='data' formaction="/update" formmethod="post">Update</button>
        <button type='submit' form='data' formaction="/delete" formmethod="post">Delete</button>
        <br>
        <br>
        <b><h1>List of users in the database</h1></b>
        <br>
        <br>

        <%
            List<User> listUsers = new LinkedList<>();
            try {
                listUsers = userStore.getAll();
            } catch (RepositoryException e) {
                LOG.error("An error occurred while retrieving the list of all users. ", e);
            }

            if (listUsers.size() > 0) {
        %>
                <table>
                    <tr>
                        <td>ID</td>
                        <td>Login</td>
                        <td>Name</td>
                        <td>E-mail</td>
                        <td>Data create</td>
                    </tr>

        <%
                for (User user : listUsers) {
        %>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getLogin()%></td>
                    <td><%=user.getName()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.getCreateDate()%></td>
                </tr>
        <%
                }
        %>
                </table>
        <%
            }
        %>

    </body>
</html>