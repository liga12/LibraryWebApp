<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/returnBook" method="get">
    <table>
        <tr>
            <td><label for="idUser">ID пользователя </label></td>
            <td><input type="text" placeholder="Введите ID пользователя" required name="idUser" id="idUser"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="next" value="Далее"/></td>
        </tr>
    </table>
    <c:set var="data" value="${requestScope.data}"/>
    <c:out value="${data}"/>
</form>
<hr/>
<jsp:include page="searchUser.jsp"/>
</body>
</html>
