
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form action="/addBook" method="get">
    <table>
        <tr>
            <td>Ведите название книги</td>
        </tr>
        <tr>
            <td><input type="text" required name="name"></td>
        </tr>
        <tr>
            <td>Введите описание(Не обязательно)</td>
        </tr>
        <tr>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>

            <td>Имя автора</td>
        </tr>
        <tr>
            <td><input type="text" required name="nameAuthor"></td>
        </tr>
        <tr>
        <td>Отчество автора(Не обязательно)</td>
    </tr>
        <tr>
            <td><input type="text" name="patronymic"></td>
        </tr>
        <tr>
            <td>Фамилия автора</td>
        </tr>
        <tr>
            <td><input type="text" required name="surname"></td>
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
