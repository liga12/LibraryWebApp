<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск клиента</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<c:set var="action" value="${requestScope.action}"/>
<form action="${action};" method="get">
    <table>
        <tr>
            <td><input type="submit" name="all" value="Ввывести все пользователей"></td>
        </tr>
    </table>
</form>
<form action=""${action}; method="get">
<table>
    <tr>
    <td><label for="find">Выберите варианты поиска</label></td>
    <td>
        <select name="find" id="find">
            <option selected value="findUserByName">По имени</option>
            <option value="findUserBySurname">По фамилии</option>
            <option value="findUserByPatronymic">По отчеству</option>
        </select>
    </td>
</tr>
<tr>
    <td>
        <input type="text" required name="query" placeholder="Введите слово для поиска" >
    </td>
</tr>
<tr>
    <td><input type="submit"  name="search" value="Поиск"></td>
</tr>
</table>
</form>
<hr>

<c:set var="size" value="${requestScope.listSize}"/>
<c:if test="${size > 0}">
    <table border="1" cellpadding="10" width="100%">
        <tr>
            <td width=\"5%\">ID</td>
            <td width=\"10%\">Имя</td>
            <td>Отчество</td>
            <td width=\"10%\">Фамилия</td>
            <td width=\"10%\">Телефон</td>
        </tr>
        <c:forEach items="${requestScope.list}" var="list">
            <tr>
                <td><c:out value="${list.get(0)}"></c:out></td>
                <td><c:out value="${list.get(1)}"></c:out></td>
                <td><c:out value="${list.get(2)}"></c:out></td>
                <td><c:out value="${list.get(3)}"></c:out></td>
                <td><c:out value="${list.get(4)}"></c:out></td>
            </tr>
        </c:forEach>
        <tr>
        </tr>
    </table>
</c:if>
<c:if test="${size < 1}">Ничего не нашел </c:if>
</body>
</html>
