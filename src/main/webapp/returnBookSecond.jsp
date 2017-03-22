<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="size" value="${requestScope.listSize}"/>
<c:if test="${size > 0}">
<form action="/returnBookSecond" method="get">
    <table>
        <tr>
            <td>Введи id из предложенных</td>
        </tr>
        <tr>
            <td><input type="text"  required name="idRent"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="add" value="Добавить"/></td>
        </tr>
    </table>
</form>
</c:if>
<c:set var="data" value="${requestScope.data}"/>
<c:out value="${data}"/>

<c:set var="size" value="${requestScope.listSize}"/>
<c:if test="${size > 0}">
    <table border="1" cellpadding="10" width="100%">
        <tr>
            <td width=\"5%\">ID</td>
            <td width=\"10%\">Имя пользователя</td>
            <td>Фамилия пользователя</td>
            <td width=\"10%\">Название книги</td>
            <td width=\"10%\">Фамилия автора</td>
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
<c:if test="${size < 1}">У пользователя нет книг в аренде </c:if>


</body>
</html>
