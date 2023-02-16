<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />


<jsp:include page="/WEB-INF/partials/searchAds.jsp" />

<c:if test="${searchTerm == null || searchTerm.length() < 1}">

<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2><form method="post" action="/ads" class="inline">
                <input type="hidden" name="viewId" value="${ad.id}">
                <button type="submit" name="viewId" value="${ad.id}" class="link-button">
                        ${ad.title}</button>
            </form></h2>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</div>
</c:if>

<c:if test="${found != null && searchTerm.length() > 0}">

<div class="container">
    <h1>Matches with: ${searchTerm}</h1>

    <c:forEach var="ad" items="${found}">
        <div class="col-md-6">
                <h2><form method="post" action="/ads" class="inline">
                    <input type="hidden" name="viewId" value="${ad.id}">
                    <button type="submit" name="viewId" value="${ad.id}" class="link-button">
                            ${ad.title}</button>
                </form></h2>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</div>
</c:if>


</body>
</html>
