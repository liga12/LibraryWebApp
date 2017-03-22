<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/addUser" method="get">
    <table>
        <tr>
            <td><label for="name">Имя</label></td>
            <td><input type="text" placeholder="Введите имя" required name="name" id="name"></td>
        </tr>
        <tr>
            <td><label for="patronymic">Отчество(Не обязательно)</label></td>
            <td><input type="text" placeholder="Введите отчество" name="patronymic" id="patronymic"></td>
        </tr>
        <tr>
            <td><label for="surname">Фамилия</label></td>
            <td><input type="text" placeholder="Введите фамилию" required name="surname" id="surname"></td>
        </tr>
        <tr>
            <td><label>Телефон(Не обязательно)</label></td>
            <td><input type="number" min="0" placeholder="Введите телефон" name="phone" id="phone"></td>
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
