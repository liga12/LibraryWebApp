<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 27.10.2016
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user/create" method="post">
    <table>
        <tr>
            <td align="right">Login:</td>
            <td>
                <input type="text" name="login">
            </td>
        </tr>
        <tr>
            <td align="right">Email:</td>
            <td>
                <input type="text" name="email">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="centre" value="Submit"> </td>
        </tr>
    </table>
</form>
</body>
</html>
