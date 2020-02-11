<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<myTags:toHompage/>
<head>
    <title>New user</title>
</head>

<body>
<h1>Registration</h1>
<form action="registration" method="post">
    <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td>Repeat your password:</td>
            <td><input type="password" name="repeatPassword" required></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" required></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="tel" name="phone" pattern="[0-9]{3} [0-9]{3} [0-9]{4}" title="format: xxx xxx xxxx"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    var Msg ='<%=(String)request.getAttribute("error-msg")%>';
    if (Msg!="null") {
        alert(Msg);
    }
</script>
<%--<c:if test="${error-msg} != null">--%>
    <%--<div id="error-window-wrapper">--%>
        <%--<div id="error-window">--%>
            <%--<a href="#" onclick="document.getElementById('error-window-wrapper').style.display='none';return false;" id="close_popup">--%>
                <%--<p id="error-text">${error-msg}</p>--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</c:if>--%>

</body>
</html>