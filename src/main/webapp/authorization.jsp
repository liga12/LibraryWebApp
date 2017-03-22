<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 26.10.2016
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:out value="${12+7}" />
  <form action="startServlet" method="post">
    Login:<input type="text" name="login"/><br/>
    Password:<input type="password" name="password"/><br/>
    <input type="submit" name="register" value="Registration">
  </form>
  </body>
</html>
