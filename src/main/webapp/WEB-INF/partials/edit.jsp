<div class="container">
  <h1 class="edit-profile-h1">Edit Profile Information</h1>
  <div class="hr" id="hr"></div>
  <form action="/profile" method="post">
    <div class="form-group">
      <label for="username">Username</label>
      <input id="username" name="username" class="form-control" type="text" value="${sessionScope.user.username}">
    </div>
    <div class="form-group">
      <label for="email">Edit Email</label>
      <input id="email" name="email" class="form-control" type="text" value="${sessionScope.user.email}">
    </div>
    <div class="form-group">
      <label for="password">New Password</label>
      <input id="password" name="password" class="form-control" type="password" placeholder="New Password">
    </div>
    <div class="form-group">
      <label for="confirm_password">Confirm New Password</label>
      <input id="confirm_password" name="confirm_password" class="form-control" type="password" placeholder="Confirm password">
    </div>
    <input type="submit" class="btn btn-primary btn-block">
  </form>
</div>