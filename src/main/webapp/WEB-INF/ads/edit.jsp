<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Viewing All The Ads"/>
  </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
  <h1>Edit Ad</h1>
  <div class="hr"></div>

  <form method="post" action="/updateAd">
    <input type="hidden" name="id" value="${ad.id}"/>

    <label for="title">Title:</label>
    <input type="text" name="title" id="title" value="${ad.title}"/>

    <label for="description">Description:</label>
    <textarea name="description" id="description">${ad.description}</textarea>

    <button type="submit" class="btn">Save Changes</button>
  </form>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>
</div>

</body>
</html>
