<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Complaint Update Successful </title>
</head>
<body>
<h1> Hi ${userName}, Your Complaint has been successfully updated with Complaint Id: ${complaintId}</h1>
<form action="cancel">
<input type="hidden" name="userName" value="${sessionScope.userName}">
	<input name="cancel" type="submit" value="Return to Home">
</form>
</body>
</html>