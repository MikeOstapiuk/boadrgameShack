<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
<myTags:gameSearch/>
<myTags:toHompage/>
<head>
    <title>Game list</title>
</head>

<body>
<table border="2">
    <tr>
        <th>Game</th>
        <th>Price</th>
        <th>Time to play</th>
        <th>Players</th>
        <th>Description</th>
        <th>Language</th>
        <th>Publishing house</th>
    </tr>
    <c:forEach var="game" items="${games}">
        <tr>
            <td>
                <a href="/gameView?id=${game.id}">
                        ${game.name}
                </a>
            </td>
            <td>${game.price}</td>
            <td>${game.timeToPlay}</td>
            <td>${game.playerNumber}</td>
            <td>${game.description}</td>
            <td>${game.language}</td>
            <td>${game.publishingHouse.name}</td>
            <td>
                <form action="/admin/gameUpdate" method="get">
                    <input type="hidden" name="id" value="${game.id}">
                    <input type="submit" value="Edit">
                </form>
                <form action="/admin/gameDelete" method="get">
                <input type="hidden" name="id" value="${game.id}">
                <input type="submit" value="Delete">
            </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>