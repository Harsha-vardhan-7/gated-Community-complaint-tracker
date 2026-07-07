<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
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
		First Name: <input type="text" name="firstname"
			placeholder="Enter First Name here" value="${param.firstname}">
		<br>
		<p style="color: red;">${firstNameErrorMessage}</p>

		Last Name: <input type="text" name="lastname"
			placeholder="Enter Last Name here" value="${param.lastname}"><br>
		<br>
		<p style="color: red;">${lastNameErrorMessage}</p>

		Flat Number: <input type="text" name="flatnumber"
			placeholder="Enter Your Flat Number here" value="${param.flatnumber}"><br>
		<p style="color: red;">${FlatNoerrorMessage}</p>
		<p style="color: red;">${flatParseErrorMessage}</p>

		Mobile Number: <input type="text" name="mobile"
			placeholder="Enter Your Mobile Number here" value="${param.mobile}"><br>
		<br>
		<p style="color: red;">${mobileNoErrorMessage}</p>

		Password: <input type="text" name="password"
			placeholder="Enter Your Password here" value="${param.password}"><br>
		<br>
		<p style="color: red;">${passworErrorMessage}</p>

		Role: <select name="role" onchange="toggleAdminCode()">
			<option value="">-- Select Role --</option>
			<option value="ADMIN" ${param.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
			<option value="RESIDENT"
				${param.role == 'RESIDENT' ? 'selected' : ''}>Resident</option>
		</select><br>
		<br>
		<p style="color: red;">${roleErrorMessage}</p>

		<div id="adminCodeField" style="display: none;">
			Admin Authentication Code: <input type="text" name="admincode"
				placeholder="Enter Admin Authentication Code"
				value="${param.admincode}"><br>
			<br>
			<p style="color: red;">${adminCodeErrorMessage}</p>
		</div>

		<input name="signup" type="submit" value="Sign Up">
		<p style="color: red;">${errorMessage}</p>

	</form>


	<p>
		Already have the Account? please click here to <a href="loginredirect">
			Login </a>
</body>
</html>