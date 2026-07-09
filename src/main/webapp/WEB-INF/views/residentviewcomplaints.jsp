<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Complaints</title>
<style>
	table {
		width: 100%;
		border-collapse: collapse;
	}
	th, td {
		padding: 10px;
		text-align: left;
	}
	th {
		background-color: #4CAF50;
		color: white;
	}
	tr:nth-child(even) {
		background-color: #f2f2f2;
	}
	.edit-link {
		padding: 6px 10px;
		background-color: #008CBA;
		color: white;
		text-decoration: none;
		border-radius: 4px;
	}
	.edit-link:hover {
		background-color: #007399;
	}
	.cancel-btn {
		margin-top: 20px;
		padding: 10px 20px;
		background-color: #f44336;
		color: white;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}
	.cancel-btn:hover {
		background-color: #da190b;
	}
</style>
</head>
<body>

	<table border=1>
	
		<tr>
			<th>Complaint ID</th>
			<th>Category</th>
			<th>Priority</th>
			<th>Status</th>
			<th>Edit </th>
		</tr>

		<c:forEach items="${complaints}" var="complaint">
			<tr>
				<td>${complaint.complaintId}</td>
				<td>${complaint.category}</td>
				<td>${complaint.priority}</td>
				<td>${complaint.status}</td>
				<td><a class="edit-link" href="edit?complaintId=${complaint.complaintId}">
						Edit </a></td>
			</tr>
		</c:forEach>
		
	</table>
	
	<p style="color: black;">${noComplaintsError}</p>

	<form action="cancel">
		<input type="hidden" name="userName" value="${sessionScope.userName}">
		<input name="cancel" type="submit" value="Cancel" class="cancel-btn">
	</form>
</body>
</html>