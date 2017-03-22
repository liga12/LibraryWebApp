<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="logout.jsp"></jsp:include>
<html>
<head>
    <title>Administrator</title>
</head>
<body>
<h4>Привет администратор)</h4>
<form action="/user" method="get">
    <input type="submit" name="searchBook" value="Найти книгу">
    <input type="submit" name="searchUser" value="Найти клиента">
    <input type="submit" name="allAuthorization" value="Ввести все  данные авторизации">
    <input type="submit" name="addAuthorization" value="Добавить данные в аторизацию">
    <input type="submit" name="addBook" value="Добавить книгу">
</form>
<hr>
</body>
</html>
