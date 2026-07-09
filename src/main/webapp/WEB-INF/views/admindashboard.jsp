<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome ${sessionScope.userName}! </h1>
	<form action="raiseComplaint" >
		<input name="raiseTheComplaint" type="submit" value="Raise Complaint" >
	</form>
	
	<form action="adminViewComplaints">
	<input name="View complaints" type="submit" value="View All Complaints">
	</form>
	
	<form action="viewadminResolvedComplaints">
	<input name="View Resolved complaints" type="submit" value="View Resolved Complaints">
	</form>
	
	<form action="viewadminPendingComplaints">
	<input name="View Pending complaints" type="submit" value="View Pending Complaints">
	</form>
	
	<form action="logout">
	<input name="logout" type="submit" value="Logout">
	</form>
</body>
</html>