<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - View All Complaints</title>
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
	.status-select {
		padding: 5px;
		border-radius: 4px;
	}
	.update-btn {
		padding: 5px 10px;
		background-color: #008CBA;
		color: white;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}
	.update-btn:hover {
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

	<h2>All Complaints - Admin View</h2>

	<table border=1>
		<tr>
			<th>Complaint ID</th>
			<th>Resident Name</th>
			<th>Flat Number</th>
			<th>Category</th>
			<th>Description</th>
			<th>Priority</th>
			<th>Status</th>
			<th>Update Status</th>
		</tr>

		<c:forEach items="${complaints}" var="complaint">
			<tr>
				<td>${complaint.complaintId}</td>
				<td>${complaint.resident.firstName} ${complaint.resident.lastName}</td>
				<td>${complaint.resident.flatNumber}</td>
				<td>${complaint.category}</td>
				<td>${complaint.description}</td>
				<td>${complaint.priority}</td>
				<td>${complaint.status}</td>
				<td>
					<form action="updateComplaintStatus" method="post" style="display: inline;">
						<input type="hidden" name="complaintId" value="${complaint.complaintId}">
						<select name="status" class="status-select">
							<option value="Pending" ${complaint.status == 'Pending' ? 'selected' : ''}>Pending</option>
							<option value="In Progress" ${complaint.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
							<option value="Resolved" ${complaint.status == 'Resolved' ? 'selected' : ''}>Resolved</option>
							<option value="Closed" ${complaint.status == 'Closed' ? 'selected' : ''}>Closed</option>
						</select>
						<input type="submit" value="Update" class="update-btn">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>

	<form action="cancel">
		<input type="hidden" name="userName" value="${sessionScope.userName}">
		<input name="cancel" type="submit" value="Cancel" class="cancel-btn">
	</form>
</body>
</html>