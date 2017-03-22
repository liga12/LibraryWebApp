<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="${requestScope.data};"/>
<form action="/editUser" method="get">
    <table>
        <tr>
            <td>Введите ID</td>
        </tr>
        <tr>
            <td><input type="text" required name="id"></td>
        </tr>
        <tr>
            <td>Введите имя</td>
        </tr>
        <tr>
            <td><input type="text" required name="name"></td>
        </tr>
        <tr>
            <td>Введите отчество(Не обязательно)</td>
        </tr>
        <tr>
            <td><input type="text" name="patronymic"></td>
        </tr>
        <tr>
            <td>Введите фамилию</td>
        </tr>
        <tr>
            <td><input type="text" required name="surname"></td>
        </tr>
        <tr>
            <td>Введите телефон(Не обязательно)</td>
        </tr>
        <tr>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td><input type="submit" name="add" value="Добавить"></td>
        </tr>
    </table>
</form>
<hr>
<jsp:include page="searchUser.jsp"/>
</body>
</html>
