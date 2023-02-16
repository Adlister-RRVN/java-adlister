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
                <h2>Title: ${ad.title}</h2>
                <div class="hr"></div>
                <p>Description: ${ad.description}</p>
                <p>Categories<ul>
            <c:forEach var="category" items="${categoryAd}">
                <li>${category.name}</li>
            </c:forEach>
            </ul></p>
                <p>Ad created by: ${userAd.username}</p>
                <p>Email user at: ${userAd.email}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
