<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1>Please Log In</h1>
    <%
        // Retrieve the error message attribute from the session, if it exists
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%= errorMessage %>
    </div>
    <%
            // Remove the error message attribute from the session so that it doesn't persist
            request.getSession().removeAttribute("errorMessage");
        }
    %>
    <form action="/login" method="POST">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input id="username" name="username" class="form-control" type="text" placeholder="Username">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input id="password" name="password" class="form-control" type="password" placeholder="Password">
        </div>
        <input type="submit" class="btn btn-primary btn-block" value="Log In">
    </form>
</div>

</body>
</html>
