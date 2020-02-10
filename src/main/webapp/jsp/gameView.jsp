<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

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
</ul>
</body>
</html>