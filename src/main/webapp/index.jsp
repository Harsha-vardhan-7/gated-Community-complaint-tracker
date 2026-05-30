<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	<form action="signup" method="post">
		First Name: <input type="text" name="firstname" placeholder="Enter First Name here"> <br><br>
		Last Name: <input type="text" name="lastname" placeholder="Enter Last Name here"><br><br>
		Flat Number: <input type="text" name="flatnumber" placeholder="Enter Your Flat Number here"><br><br>
		Mobile Number: <input type="text" name="mobile" placeholder="Enter Your Mobile Number here"><br><br>
		Password: <input type="text" name="password" placeholder="Enter Your Password here"><br><br>
		<input name="signup" type="submit" value="Sign Up">
</form>

<p>Already have the Account? please click here to <a href="loginredirect"> Login </a>

</body>
</html>