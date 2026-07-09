<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complaint Filing</title>
</head>
<body>
<form action="complaintsubmit" method="post">
	Name: <br><input name="name" type="text" value="${sessionScope.userName}"><br><br>
	Mobile Number:<br> <input name="phoneNumber" type="text" value="${sessionScope.phoneNumber}"><br><br>
	Flat Number: <br><input name="flatNo" type="text" value="${sessionScope.flatNumber}"><br><br>
	Category: <br><input name="category" type="text" placeholder="Enter your Complaint category here"><br>
	<p style="color: red;">${categoryErrorMessage}</p>
	Description of Complaint:<br> <textarea id="description" name="description" rows="5" cols="40" placeholder="Enter full details here"></textarea><br>
	<p style="color: red;">${descriptionErrorMessage}</p>
	
	<label for="priority">Choose a priority:</label><br>
    <select name="priority" id="priority">
        <option value="high">High</option>
        <option value="medium">Medium</option>
        <option value="low">Low</option>
    </select><br><br>
    
    <input name="submitComplaint" type="submit" value="Submit">
</form>

<form action="cancel">
<input type="hidden" name="userName" value="${sessionScope.userName}">
	<input name="cancel" type="submit" value="Cancel">
</form>
</body>
</html>