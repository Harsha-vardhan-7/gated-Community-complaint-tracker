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
		First Name: <input type="text" name="firstname" placeholder="Enter First Name here"> <br>
		<p style="color:red;"> ${firstNameErrorMessage}</p>
		
		Last Name: <input type="text" name="lastname" placeholder="Enter Last Name here"><br><br>
		<p style="color:red;"> ${lastNameErrorMessage}</p>
		
		Flat Number: <input type="text" name="flatnumber" placeholder="Enter Your Flat Number here"><br>
		<p style="color:red;"> ${FlatNoerrorMessage}</p>
		<p style="color:red;"> ${flatParseErrorMessage}</p>
		
		Mobile Number: <input type="text" name="mobile" placeholder="Enter Your Mobile Number here"><br><br>
		<p style="color:red;"> ${mobileNoErrorMessage}</p>
		
		Password: <input type="text" name="password" placeholder="Enter Your Password here"><br><br>
		<p style="color:red;"> ${passworErrorMessage}</p>
		
		<input name="signup" type="submit" value="Sign Up">
		<p style="color:red;"> ${errorMessage}</p>
		
</form>


<p>Already have the Account? please click here to <a href="loginredirect"> Login </a>

</body>
</html>