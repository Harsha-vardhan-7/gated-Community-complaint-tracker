<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	body {
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		min-height: 100vh;
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 20px;
	}
	form {
		max-width: 450px;
		width: 100%;
		background: #ffffff;
		padding: 40px;
		border-radius: 10px;
		box-shadow: 0 8px 25px rgba(0,0,0,0.2);
	}
	form h2 {
		color: #333;
		margin-bottom: 30px;
		text-align: center;
		font-size: 28px;
	}
	label {
		display: block;
		margin-bottom: 8px;
		color: #555;
		font-weight: 600;
		font-size: 14px;
	}
	input[type="text"], input[type="password"], select {
		width: 320px;
		max-width: 100%;
		padding: 12px 15px;
		margin-bottom: 20px;
		border: 2px solid #e0e0e0;
		border-radius: 6px;
		font-size: 14px;
		transition: border-color 0.3s, box-shadow 0.3s;
	}
	input[type="text"]:focus, input[type="password"]:focus, select:focus {
		outline: none;
		border-color: #667eea;
		box-shadow: 0 0 8px rgba(102, 126, 234, 0.2);
	}
	input[type="submit"], .btn {
		width: 100%;
		padding: 12px 20px;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		color: white;
		border: none;
		border-radius: 6px;
		cursor: pointer;
		font-size: 16px;
		font-weight: 600;
		transition: transform 0.2s, box-shadow 0.2s;
		margin-top: 10px;
	}
	input[type="submit"]:hover, .btn:hover {
		transform: translateY(-2px);
		box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
	}
	p {
		margin: 15px 0;
		font-size: 13px;
	}
	p.error, p[style*="color: red"] {
		color: #e74c3c;
		margin-top: -15px;
		margin-bottom: 15px;
	}
	a {
		color: #667eea;
		text-decoration: none;
		font-weight: 600;
	}
	a:hover {
		text-decoration: underline;
	}
</style>
<script type="text/javascript">
	function toggleAdminCode() {
		var role = document.getElementsByName("role")[0].value;
		var adminCodeField = document.getElementById("adminCodeField");
		
		if (role === "ADMIN") {
			adminCodeField.style.display = "block";
		} else {
			adminCodeField.style.display = "none";
		}
	}
	
	// Show admin field on page load if role is already ADMIN (for error cases)
	window.onload = function() {
		toggleAdminCode();
	}
</script>
</head>
<body>
	<form action="signup" method="post">
		<h2>Create Account</h2>
		
		<label>First Name</label>
		<input type="text" name="firstname" placeholder="Enter First Name" value="${param.firstname}">
		<p style="color: red;">${firstNameErrorMessage}</p>

		<label>Last Name</label>
		<input type="text" name="lastname" placeholder="Enter Last Name" value="${param.lastname}">
		<p style="color: red;">${lastNameErrorMessage}</p>

		<label>Flat Number</label>
		<input type="text" name="flatnumber" placeholder="Enter Your Flat Number" value="${param.flatnumber}">
		<p style="color: red;">${FlatNoerrorMessage}</p>
		<p style="color: red;">${flatParseErrorMessage}</p>

		<label>Mobile Number</label>
		<input type="text" name="mobile" placeholder="Enter Your Mobile Number" value="${param.mobile}">
		<p style="color: red;">${mobileNoErrorMessage}</p>

		<label>Password</label>
		<input type="text" name="password" placeholder="Enter Your Password" value="${param.password}">
		<p style="color: red;">${passworErrorMessage}</p>

		<label>Role</label>
		<select name="role" onchange="toggleAdminCode()">
			<option value="">-- Select Role --</option>
			<option value="ADMIN" ${param.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
			<option value="RESIDENT" ${param.role == 'RESIDENT' ? 'selected' : ''}>Resident</option>
		</select>
		<p style="color: red;">${roleErrorMessage}</p>

		<div id="adminCodeField" style="display: none;">
			<label>Admin Authentication Code</label>
			<input type="text" name="admincode" placeholder="Enter Admin Authentication Code" value="${param.admincode}">
			<p style="color: red;">${adminCodeErrorMessage}</p>
		</div>

		<input name="signup" type="submit" value="Sign Up" class="btn">
		<p style="color: red;">${errorMessage}</p>
		
		<p style="text-align: center; margin-top: 20px;">Already have an Account? <a href="loginredirect">Login</a></p>
	</form>
</body>
</html>