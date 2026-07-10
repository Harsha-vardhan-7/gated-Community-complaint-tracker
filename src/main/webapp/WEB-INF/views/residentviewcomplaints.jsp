<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Complaints</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
	<div class="container">
        <header class="app-header">
            <div class="app-brand">
                <div class="logo">RH</div>
                <div>
                    <div class="app-title">My Complaints</div>
                    <div class="app-meta">${sessionScope.userName}</div>
                </div>
            </div>
			<div class="header-actions">
				<form action="cancel"><input type="hidden" name="userName" value="${sessionScope.userName}"><input class="btn btn-secondary" type="submit" name="cancel" value="Back"></form>
			</div>
		</header>

		<div class="card">
			<table class="table">
				<thead>
					<tr>
						<th>Complaint ID</th>
						<th>Category</th>
						<th>Priority</th>
						<th>Status</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${complaints}" var="complaint">
						<tr>
							<td>${complaint.complaintId}</td>
							<td>${complaint.category}</td>
							<td>${complaint.priority}</td>
							<td><span class="badge ${complaint.status == 'Pending' ? 'pending' : (complaint.status == 'In Progress' ? 'inprogress' : (complaint.status == 'Resolved' ? 'resolved' : 'closed'))}">${complaint.status}</span></td>
							<td><a class="link-btn" href="edit?complaintId=${complaint.complaintId}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<p class="text-muted">${noComplaintsError}</p>
		</div>
	</div>
</body>
</html>