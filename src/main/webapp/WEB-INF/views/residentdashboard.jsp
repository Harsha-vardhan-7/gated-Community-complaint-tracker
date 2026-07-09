<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome ${userName}! </h1>
	<form action="raiseComplaint" >
	<input type="hidden" name="userName" value="${sessionScope.userName}">
	<input type="hidden" name="phoneNumber" value="${sessionScope.phoneNumber}">
	<input type="hidden" name="flatNumber" value="${sessionScope.flatNumber}">
		<input name="raiseTheComplaint" type="submit" value="Raise the Complaint" >
	</form>
	
	<form action="residentViewComplaints">
	<input name="View complaints" type="submit" value="View Complaints">
	</form>
	
	<form action="viewResolvedComplaints">
	<input name="View complaints" type="submit" value="View Resolved Complaints">
	</form>
	
	<form action="logout">
	<input name="logout" type="submit" value="Logout">
	</form>
</body>
</html>