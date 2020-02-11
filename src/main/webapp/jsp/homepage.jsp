<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BoardgameShack</title>
</head>
<body>
<myTags:gameSearch/>
<myTags:login/>
<c:if test="${sessionScope.user != null}">
    <a href="/admin/adminPage">Admin panel</a>
</c:if>

<h1>Welcome!</h1>
<a href="/admin/gameSave">Add new game</a>
<a href="registration">Registration</a>
<a href="gameList">All games</a>
<a href="/logout">Logout</a>
</body>
</html>