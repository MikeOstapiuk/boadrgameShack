<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8"%>

<a href = "/homepage">
    Homepage
</a>
<c:if test="${sessionScope.user != null}">
    <a href="/admin/adminPage">Admin panel</a>
</c:if>