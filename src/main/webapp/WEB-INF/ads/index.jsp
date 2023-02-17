<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container d-flex justify-content-center">
    <h1 class="all-the-ads">Car listings</h1>
</div>

<jsp:include page="/WEB-INF/partials/searchAds.jsp"/>

<c:if test="${searchTerm == null || searchTerm.length() < 1}">
<div class="container">
    <h1>Here are all the ads!</h1>
    <div class="hr"></div>
    <div class="container-all-ads">
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <h2>
                    <form method="post" action="/ads" class="inline">
                        <input type="hidden" name="viewId" value="${ad.id}">
                        <button type="submit" name="viewId" value="${ad.id}" class="link-button">
                                ${ad.title}</button>
                    </form>
                </h2>
                <div class="hr"></div>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
    </div>
</div>
</c:if>

<c:if test="${found.size() > -1 && searchTerm.length() > 0}">
<div class="container">
    <h1>Title or Description Match: ${searchTerm}</h1>
    <div class="hr"></div>
    <div class="container-all-ads">
        <c:forEach var="ad" items="${found}">
            <div class="col-md-6">
                <h2>
                    <form method="post" action="/ads">
                        <input type="hidden" name="viewId" value="${ad.id}">
                        <button type="submit" name="viewId" value="${ad.id}" class="link-button">${ad.title}</button>
                    </form>
                </h2>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
    </div>
</div>
</c:if>

<c:if test="${catsearch.size() > 0 && searchTerm.length() > 0}">
<div class="container">
    <h1>Category Match: ${searchTerm}</h1>
    <div class="hr"></div>
    <div class="container-all-ads">
        <c:forEach var="ad" items="${catsearch}">
            <div class="col-md-6">
                <h2>
                    <form method="post" action="/ads">
                        <input type="hidden" name="viewId" value="${ad.id}">
                        <button type="submit" name="viewId" value="${ad.id}" class="link-button">${ad.title}</button>
                    </form>
                </h2>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
    </div>
</div>
</c:if>

<c:if test="${usersearch.size() > 0 && searchTerm.length() > 0}">
<div class="container">
    <h1>User Match: ${searchTerm}</h1>
    <div class="hr"></div>
    <div class="container-all-ads">
        <c:forEach var="ad" items="${usersearch}">
            <div class="col-md-6">
                <h2>
                    <form method="post" action="/ads">
                        <input type="hidden" name="viewId" value="${ad.id}">
                        <button type="submit" name="viewId" value="${ad.id}"
                                class="link-button">${ad.title}</button>
                    </form>
                </h2>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
    </div>
</div>
</c:if>
