<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<myTags:toHompage/>
<head>
    <title>Update</title>
</head>

<body>
<h1>Update ${game.name}:</h1>
<form action="/admin/gameUpdate" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" value="${game.name}" name="name" required></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" value = "${game.price}" name="price" step="0.01" min = "0" required></td>
        </tr>
        <tr>
            <td>Time to play:</td>
            <td><input type="text" value = "${game.timeToPlay}" name="timeToPlay"></td>
        </tr>
        <tr>
            <td>Number of players:</td>
            <td><input type="text" value = "${game.playerNumber}" name="playerNumber"></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" value = "${game.description}" name="description"></td>
        </tr>
        <tr>
            <td>Language:</td>
            <td><input type="text" value = "${game.language}" name="language"></td>
        </tr>
        <tr>
            <td>Publishing house:</td>
            <td><input type="text" value = "${game.publishingHouse.name}" name="publishingHouse"></td>
        </tr>
        <tr>
            <td>Categories:</td>
            <c:forEach var = "category" items = "${categories}">
                <td><input type="checkbox" name="categoryArray" value="${category.name}"></td>
                <td>${category.name}</td><br>
            </c:forEach>
        </tr>
        <input type="hidden" name="id" value="${game.id}">
        <tr>
            <td></td>
            <td><input type="submit" value="Save changes"></td>
        </tr>
    </table>
</form>
</body>
</html>