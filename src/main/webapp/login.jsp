
<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 26.10.2016
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form action="/login" method="post">
    <table>
        <tr>
            <td><label for="login">Login</label></td>
            <td><input type="text" placeholder="Введите логин" required name="login" id="login"/></td>
        </tr>

        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" placeholder="Введите пароль" required name="password" id="password"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="log" value="Ввойти"/>
            </td>
        </tr>

    </table>
</form>
<c:out value="${requestScope.error }"/>
</body>
</html>
