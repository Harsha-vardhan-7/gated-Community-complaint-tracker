<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
</body>
<body>
	<div class="container" style="max-width:420px;margin:72px auto">
		<div class="form-card">
			<form action="login" method="post">
				<h2 style="margin:0 0 16px 0">Login</h2>
				<div class="form-row">
					<label>Username</label>
					<input name="username" type="text" placeholder="Enter Mobile Number">
				</div>
				<div class="form-row">
					<label>Password</label>
					<input name="password" type="password" placeholder="Enter your password">
				</div>
				<div class="form-row">
					<input type="submit" name="login" value="Login" class="btn btn-primary">
				</div>

				<p class="helper">${errorMessage}</p>
				<p class="helper">${roleErrorMessage}</p>
				<p class="helper">${emptyFields}</p>

				<p class="center mt-8">New User? <a href="signup">Sign Up</a></p>
			</form>
		</div>
	</div>
</body>
</html>