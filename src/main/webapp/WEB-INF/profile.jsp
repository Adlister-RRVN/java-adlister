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
  <h1 class="h1-welcome-user">Welcome, ${sessionScope.user.username}! <br>Here are your Ads</h1>
  <div class="hr" id="hr"></div>
</div>
<div class="container">
<%--  <h1 class="here-are-your-ads">Here are all your ads, ${sessionScope.`user.username}!</h1>--%>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
