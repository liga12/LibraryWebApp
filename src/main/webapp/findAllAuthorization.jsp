
<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 07.11.2016
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" cellpadding="10" width="100%">
    <tr>
    <td width=\"5%\">Тип</td>
    <td width=\"10%\">Логин</td>
    <td>Пароль</td>
    </tr>
    <c:forEach items="${requestScope.list}" var="list">
        <tr>
            <td><c:out value="${list.get(0)}"></c:out></td>
            <td><c:out value="${list.get(1)}"></c:out></td>
            <td><c:out value="${list.get(2)}"></c:out></td>
        </tr>
    </c:forEach>
    <tr>
    </tr>
    </table>
</body>
</html>
