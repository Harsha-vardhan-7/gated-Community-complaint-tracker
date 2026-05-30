<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%-- <marquee>Hi ${firstName}, You have been successfully signed up. Please login!</marquee> --%>

<form action="login" method="post">
	Username: <input name="username" type="text" placeholder="Enter Mobile Number here"><br><br>
	Password: <input name="password" type="password" placeholder="Enter your password here"><br><br>
			<input type="submit" name="login" value="Login">
</form>
<p style="color:red;"> ${errorMessage}</p>
<p>New User? Click here to <a href="signup"> Sign Up</a>
</body>
</html>