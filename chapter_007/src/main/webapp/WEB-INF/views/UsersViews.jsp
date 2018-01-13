<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <button type='submit' form='data' formaction="${requestScope.sessionContext.contextPath}/addUser" formmethod="post">Add</button>
        <button type='submit' form='data' formaction="${requestScope.sessionContext.contextPath}/updateUser" formmethod="post">Update</button>
        <button type='submit' form='data' formaction="${requestScope.sessionContext.contextPath}/deleteUser" formmethod="post">Delete</button>
        <br>
        <br>
        <b><h1>List of users in the database</h1></b>
        <br>
        <br>

        <c:if test="${sizeUsers > 0}">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Login</td>
                    <td>Name</td>
                    <td>E-mail</td>
                    <td>Data create</td>
                </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.name}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.createDate}"></c:out></td>
                </tr>
            </c:forEach>

            </table>
        </c:if>
    </body>
</html>