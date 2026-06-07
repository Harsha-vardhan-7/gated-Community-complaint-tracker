<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Complaints</title>
</head>
<body>

	<table border=1>
		<tr>
			<th>Complaint ID</th>
			<th>Category</th>
			<th>Priority</th>
			<th>Status</th>
			<th>Edit C
		</tr>

		<c:forEach items="${complaints}" var="complaint">
			<tr>
				<td>${complaint.complaintId}</td>
				<td>${complaint.category}</td>
				<td>${complaint.priority}</td>
				<td>${complaint.status}</td>
				<td><a href="edit?complaintId=${complaint.complaintId}">
						Edit </a></td>
			</tr>
		</c:forEach>

	</table>

	<form action="cancel">
		<input type="hidden" name="userName" value="${sessionScope.userName}">
		<input name="cancel" type="submit" value="Cancel">
	</form>
</body>
</html>