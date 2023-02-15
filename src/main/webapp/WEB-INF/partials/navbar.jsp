<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="Navbar__">
  <div class="Navbar__container">
    <div class="Navbar__header">
      <a class="Navbar__brand" href="/ads">
        Car Lister</a>
    </div>
    <ul class="Navbar__right">
      <c:choose>
        <c:when test="${empty sessionScope.user.username}">
          <li><a class="Navbar__right-link" href="/login">Login</a></li>
          <li><a class="Navbar__right-link" href="/register">Sign up!</a></li>
        </c:when>
        <c:otherwise>
          <li><a class="Navbar__right-link" href="/logout">Logout</a></li>
          <li><a class="Navbar__profile-icon" href="/profile">profile</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</nav>