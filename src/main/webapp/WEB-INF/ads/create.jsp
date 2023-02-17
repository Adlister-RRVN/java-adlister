<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Create a new Ad"/>
  </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
  <h1>Create a new Ad</h1>
  <form action="/ads/create" method="post">
    <div class="form-group">
      <label for="title">Title</label>
      <input id="title" name="title" class="form-control" type="text"
             value="<%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %>">
      <% if (request.getParameter("submit") != null && request.getParameter("title").isEmpty()) { %>
      <div class="alert alert-danger" role="alert">Title is required.</div>
      <% } %>
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea id="description" name="description" class="form-control"
                type="text">
        <%= request.getAttribute("description") != null ? request.getAttribute("description") : "" %>
      </textarea>

    </div>
    <input type="checkbox" id="sedan" name="sedan" value="1">
    <label for="sedan">Sedan</label><br>
    <input type="checkbox" id="grand" name="grand" value="2">
    <label for="grand">Grand Touring</label><br>
    <input type="checkbox" id="crossover" name="crossover" value="3">
    <label for="crossover">Crossover</label><br>
    <input type="checkbox" id="sav" name="sav" value="4">
    <label for="sav">SAV</label><br>
    <input type="checkbox" id="suv" name="suv" value="5">
    <label for="suv">SUV</label><br>
    <input type="checkbox" id="compact" name="compact" value="6">
    <label for="compact">Compact</label><br>
    <input type="checkbox" id="convertible" name="convertible" value="7">
    <label for="convertible">Convertible</label><br>
    <input type="checkbox" id="foreign" name="foreign" value="8">
    <label for="foreign">Foregin</label><br>
    <input type="checkbox" id="domestic" name="domestic" value="9">
    <label for="domestic">Domestic</label><br>

    <input type="submit" class="btn btn-block btn-primary" name="submit" value="Submit">
  </form>
</div>
</body>
</html>
