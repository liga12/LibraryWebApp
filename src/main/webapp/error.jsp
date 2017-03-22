
<%--
  Created by IntelliJ IDEA.
  User: LIGA
  Date: 25.10.2016
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="login.jsp"></jsp:include>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="${requestScope.error }"/>
</body>
</html>
