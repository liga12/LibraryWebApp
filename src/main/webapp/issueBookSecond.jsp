<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 11.11.2016
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/issueBookSecond" method="get">
    <table>
        <tr>
            <td><label for="bookId">ID книги</label></td>
            <td><input type="text" placeholder="Введите ID книги" required name="bookId" id="bookId"/></td>
        </tr>
        </table>
    <table>
        <tr>
            <td><label for="date">Через сколько дней вернете</label></td>
            <td><input id="date" type="range" min="1" max="14" step="1"  name="date" onchange="textBox.value = date.value"/></td>
            <td> <input id="textBox" type="text" readonly/></td>
        </tr>
        <tr>
            <td><input type="submit" name="add" value="Добавить в аренду"/></td>
        </tr>
    </table>
    <c:set var="data" value="${requestScope.data}"/>
    <c:out value="${data}"/>
    <hr/>
</form>
<jsp:include page="searchBook.jsp"/>
</body>
</html>
