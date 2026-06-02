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
	<form action="raiseComplaint">
		<input name="raiseComplaint" type="submit" value="Raise the Complaint" >
		
	</form>
	
	<form action="viewComplaints">
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