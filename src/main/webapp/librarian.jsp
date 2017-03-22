<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 26.10.2016
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="logout.jsp"></jsp:include>
<html>
<head>
    <title>Librarian</title>
</head>
<body>
<h4>Привет библиоекарь)</h4>
<form action="/user" method="get">
    <input type="submit" name="searchBook" value="Найти книгу">
    <input type="submit" name="searchUser" value="Найти клиента">
    <input type="submit" name="findAllBookByRent" value="Посмотреть книги в аренде">
    <input type="submit" name="findAllRent" value="Посмотреть список аренды">
    <input type="submit" name="addUser" value="Добавить пользователь">
    <input type="submit" name="editUser" value="Редактировать пользователя">
    <input type="submit" name="issueBook" value="Выдать книгу">
    <input type="submit" name="returnBook" value="Получить книгу">
</form>
<hr>
</body>
</html>