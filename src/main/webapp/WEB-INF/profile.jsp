<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Welcome, ${sessionScope.user.username}!</h1>
</div>
<div class="container">
    <h1>Here are all your ads ${sessionScope.user.username}!</h1>
    <c:forEach var="ad" items="${ads}">
        <c:if test="${ad.userId == sessionScope.user.id}">
            <div class="col-md-6">
                <h2>${ad.title}</h2>
                <p>${ad.description}</p>
            </div>
        </c:if>
    </c:forEach>
</div>

<jsp:include page="/WEB-INF/partials/edit.jsp"/>


</body>
</html>
