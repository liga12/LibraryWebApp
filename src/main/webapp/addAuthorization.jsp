
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/addAuthorization" method="post">
    <table>
        <tr>
            <td>Ведите логин</td>
        </tr>
        <tr>
            <td><input type="text" required name="login"></td>
        </tr>
        <tr>
            <td>Введите пароль</td>
        </tr>
        <tr>
            <td><input type="text" required name="password"></td>
        </tr>
        <tr>
            <td>Выберите вариант</td>
            <td>
                <select name="type">
                    <option selected value="1">Администратор</option>
                    <option value="2">Библиотекарь</option>
                    <option value="3">Клиент</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" name="add" value="Добавить"></td>
        </tr>
    </table>
    <c:set var="data" value="${requestScope.data}"/>
        <c:out value="${data}"/>
</form>
</body>
</html>
