<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
	<div class="container">
		<header class="app-header">
			<div class="app-brand">
				<div class="logo">RH</div>
				<div>
					<div class="app-title">ResolveHub</div>
					<div class="app-meta">Welcome, ${sessionScope.userName}</div>
				</div>
			</div>
			<div class="header-actions">
				<form action="logout"><input type="submit" class="btn btn-logout" value="Logout" name="logout"></form>
			</div>
		</header>

		<div class="action-grid">
			<div class="card">
				<h3>Raise a Complaint</h3>
				<p>Submit a new complaint for community or maintenance issues.</p>
				<div class="card-actions">
					<form action="raiseComplaint"><input name="raiseTheComplaint" type="submit" class="btn btn-primary" value="Raise Complaint"></form>
				</div>
			</div>

			<div class="card">
				<h3>View Complaints</h3>
				<p>Browse complaints by status or view all complaints.</p>
				<div class="card-actions">
					<button type="button" class="expand-btn" onclick="toggleSubmenu(this)">View Complaints</button>
					<div class="submenu" style="width:100%">
						<form action="adminViewComplaints"><input type="submit" class="btn" value="View All Complaints"></form>
						<form action="adminViewComplaintsByStatus"><input type="hidden" name="status" value="Pending"><input type="submit" class="btn" value="View Pending Complaints"></form>
						<form action="adminViewComplaintsByStatus"><input type="hidden" name="status" value="In Progress"><input type="submit" class="btn" value="View In Progress Complaints"></form>
						<form action="adminViewComplaintsByStatus"><input type="hidden" name="status" value="Resolved"><input type="submit" class="btn" value="View Resolved Complaints"></form>
						<form action="adminViewComplaintsByStatus"><input type="hidden" name="status" value="Closed"><input type="submit" class="btn" value="View Closed Complaints"></form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function toggleSubmenu(button) {
			const submenu = button.nextElementSibling || button.parentElement.querySelector('.submenu');
			button.classList.toggle('collapsed');
			submenu.classList.toggle('show');
		}
	</script>
</body>
</html>