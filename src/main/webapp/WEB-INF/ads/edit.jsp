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

  <c:forEach var="ad" items="${ads}">
    <form method="post" action="/ads/edit">
      <input type="hidden" name="id" value="${ad.id}"/>

      <label for="title">Title:</label>
      <input type="text" name="title" id="title" placeholder="${ad.title}" value=""/>

      <label for="description">Description:</label>
      <textarea name="description" id="description" placeholder="${ad.description}" value="">${ad.description}</textarea>

      <button type="submit" class="btn">Save Changes</button>
    </form>
  </c:forEach>
</div>

</body>
</html>
