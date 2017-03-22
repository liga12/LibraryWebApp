<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/issueBook" method="get">
    <table>
        <tr>
            <td><label for="userId">ID пользователя</label></td>
            <td><input type="number" placeholder="Введите ID пользователя" min="1" required name="userId" id="userId"/> </td>
        </tr>
        <tr>
            <td><input type="submit" name="next" value="Далее"/></td>
        </tr>
    </table>
    <c:set var="data" value="${requestScope.data}"/>
    <c:out value="${data}"/>
    <hr/>
</form>
<jsp:include page="searchUser.jsp"/>
</body>
</html>
