<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	body {
		font-family: Arial, sans-serif;
		margin: 20px;
	}
	form {
		max-width: 420px;
		padding: 18px;
		background: #fff;
		border-radius: 6px;
		box-shadow: 0 2px 6px rgba(0,0,0,0.1);
	}
	input[type="text"], input[type="password"] {
		width: 100%;
		padding: 8px 10px;
		margin: 8px 0;
		border: 1px solid #ccc;
		border-radius: 4px;
		box-sizing: border-box;
	}
	input[type="submit"] {
		padding: 10px 16px;
		background-color: #4CAF50;
		color: white;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}
	input[type="submit"]:hover {
		background-color: #45a049;
	}
	p.error {
		color: red;
	}
</style>
</head>
<body>

<form action="login" method="post">
	Username: <input name="username" type="text" placeholder="Enter Mobile Number here"><br><br>
	Password: <input name="password" type="password" placeholder="Enter your password here"><br><br>
									<input type="submit" name="login" value="Login">
</form>
<p style="color:red;"> ${errorMessage}</p>
<p style="color:red;"> ${roleErrorMessage}</p>
<p style="color:red;"> ${emptyFields}</p>
<p>New User? Click here to <a href="signup"> Sign Up</a>
</body>
</html>