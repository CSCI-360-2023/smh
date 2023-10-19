<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<h1>Log In Page<br></h1>
    <form action="log_in" method="post" id="aligned">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

		<label>&nbsp;</label>
        <input type="submit" value="Log In">
    </form><br>
    <a href="create_user_form.html">New User</a>
</body>
</html>