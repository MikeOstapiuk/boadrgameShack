<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<myTags:toHompage/>
<head>
    <title>Add new game</title>
</head>

<body>
<h1>Add game:</h1>
<form action="/admin/gameSave" method="post">
<table>
    <tr>
        <td>Name:</td>
        <td><input type="text" name="name" required></td>
    </tr>
    <tr>
        <td>Price:</td>
        <td><input type="number" name="price" step="0.01" min = "0" required></td>
    </tr>
    <tr>
        <td>Time to play:</td>
        <td><input type="text" name="timeToPlay"></td>
    </tr>
    <tr>
        <td>Number of players:</td>
        <td><input type="text" name="playerNumber"></td>
    </tr>
    <tr>
        <td>Description:</td>
        <td><input type="text" name="description"></td>
    </tr>
    <tr>
        <td>Language:</td>
        <td><input type="text" name="language"></td>
    </tr>
    <tr>
        <td>Publishing house:</td>
        <td><input type="text" name="publishingHouse"></td>
    </tr>
    <tr>
        <td>Categories:</td>
        <c:forEach var = "category" items = "${categories}">
            <td><input type="checkbox" name="categoryArray" value="${category.name}"></td>
            <td>${category.name}</td><br>
        </c:forEach>
    </tr>
    <tr>
        <td></td>
        <td><input type="submit" value="Submit"></td>
    </tr>
</table>
</form>
</body>
</html>