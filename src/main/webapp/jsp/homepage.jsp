<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>BoardgameShack</title>
</head>
<body>
<myTags:gameSearch/>
<myTags:login/>

<h1>Welcome!</h1>
<a href="/admin/gameSave">Add new game</a>
<a href="registration">Registration</a>
<a href="gameList">All games</a>
</body>
</html>