<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
</body>
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
				<p>Create a new complaint for maintenance or community issues.</p>
				<div class="card-actions">
					<form action="raiseComplaint"><input name="raiseTheComplaint" type="submit" class="btn btn-primary" value="Raise Complaint"></form>
				</div>
			</div>

			<div class="card">
				<h3>My Complaints</h3>
				<p>View your submitted complaints and their statuses.</p>
				<div class="card-actions">
					<form action="residentViewComplaints"><input name="View Complaints" type="submit" class="btn" value="View Complaints"></form>
				</div>
			</div>

			<div class="card">
				<h3>Resolved Complaints</h3>
				<p>Quick access to complaints that have been resolved.</p>
				<div class="card-actions">
					<form action="viewResidentResolvedComplaints"><input name="View Resolved Complaints" type="submit" class="btn" value="View Resolved Complaints"></form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>