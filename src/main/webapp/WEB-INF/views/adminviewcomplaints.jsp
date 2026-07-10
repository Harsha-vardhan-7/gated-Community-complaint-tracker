<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - View All Complaints</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
	<div class="container">
        <header class="app-header">
            <div class="app-brand">
                <div class="logo">RH</div>
                <div>
                    <div class="app-title">ResolveHub — All Complaints</div>
                    <div class="app-meta">Signed in as ${sessionScope.userName}</div>
                </div>
            </div>
			<div class="header-actions">
				<form action="cancel"><input type="hidden" name="userName" value="${sessionScope.userName}"><input class="btn btn-secondary" type="submit" name="cancel" value="Back"></form>
			</div>
		</header>

		<div class="card">
			<div class="table-wrapper">
				<table class="table">
					<thead>
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
					</thead>
					<tbody>
						<c:forEach items="${complaints}" var="complaint">
							<tr>
								<td>${complaint.complaintId}</td>
								<td>${complaint.resident.firstName} ${complaint.resident.lastName}</td>
								<td>${complaint.resident.flatNumber}</td>
								<td>${complaint.category}</td>
								<td>${complaint.description}</td>
								<td>${complaint.priority}</td>
								<td><span class="badge ${complaint.status == 'Pending' ? 'pending' : (complaint.status == 'In Progress' ? 'inprogress' : (complaint.status == 'Resolved' ? 'resolved' : 'closed'))}">${complaint.status}</span></td>
								<td>
									<form action="updateComplaintStatus" method="post" style="display:inline-flex;gap:8px;align-items:center;">
										<input type="hidden" name="complaintId" value="${complaint.complaintId}">
										<select name="status" class="status-select">
											<option value="Pending" ${complaint.status == 'Pending' ? 'selected' : ''}>Pending</option>
											<option value="In Progress" ${complaint.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
											<option value="Resolved" ${complaint.status == 'Resolved' ? 'selected' : ''}>Resolved</option>
											<option value="Closed" ${complaint.status == 'Closed' ? 'selected' : ''}>Closed</option>
										</select>
										<input type="submit" value="Update" class="btn btn-primary">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>