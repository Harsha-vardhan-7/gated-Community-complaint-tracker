<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Complaint</title>
</head>
<body>
	<form action="updateComplaint" method="post">
	Name: <br><input name="name" type="text" value="${sessionScope.userName}"><br><br>
	Mobile Number:<br> <input name="phoneNumber" type="text" value="${sessionScope.phoneNumber}"><br><br>
	Flat Number: <br><input name="flatNo" type="text" value="${sessionScope.flatNumber}"><br><br>
	Category: <br><input name="category" type="text" value="${Complaint.category}"><br>
	<p style="color: red;">${categoryErrorMessage}</p>
	Description of Complaint:<br> <textarea id="description" name="description" rows="5" cols="40">${Complaint.description}</textarea><br>
	<p style="color: red;">${categoryErrorMessage}</p>
	
	<label for="priority">Choose a priority:</label><br>
    <select name="priority" id="priority">

    <option value="high"
        ${Complaint.priority == 'high' ? 'selected' : ''}>
        High
    </option>

    <option value="medium"
        ${Complaint.priority == 'medium' ? 'selected' : ''}>
        Medium
    </option>

    <option value="low"
        ${Complaint.priority == 'low' ? 'selected' : ''}>
        Low
    </option>

</select><br><br>
    
    <input name="submitComplaint" type="submit" value="Submit">
    
    <input type="hidden" name="complaintId" value="${Complaint.complaintId}">
</form>



<form action="viewComplaints">
<%-- <input type="hidden" name="userName" value="${sessionScope.userName}"> --%>
	<input name="cancel" type="submit" value="Cancel">
</form>
</body>
</html>