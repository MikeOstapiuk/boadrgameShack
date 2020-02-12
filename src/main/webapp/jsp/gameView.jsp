<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<myTags:gameSearch/>
<myTags:toHompage/>
<head>
    <title>${model.name}</title>
</head>
<body>
<h1>
    ${model.name}
</h1>
<ul>
    <li>Price: ${model.price}</li>
    <li>Time to play: ${model.timeToPlay}</li>
    <li>Number of players: ${model.playerNumber}</li>
    <li>Description: ${model.description}</li>
    <li>Language: ${model.language}</li>
    <li>Publishing house: ${model.publishingHouse.name}</li>
    <li>Categories:</li>
    <c:forEach var="category" items="${model.getCategories()}">
        <p>${category.name}</p>
    </c:forEach>
</ul>
</body>
</html>