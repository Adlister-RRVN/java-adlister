<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />


    <div class="container">
        <h1>Ad Details</h1>

        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <h2>${ad.title}</h2>
                <p>${ad.description}</p>
                <p>${ad.id}</p>
                <p>${ad.userId}</p>
            </div>
            <div class="col-md-6">
                <h2>Ad created by: ${userAd.username}</h2>
                <p>Email user at: ${userAd.email}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
