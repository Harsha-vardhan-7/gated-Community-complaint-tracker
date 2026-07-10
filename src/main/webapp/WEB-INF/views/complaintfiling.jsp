<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complaint Filing</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
	<div class="container">
        <header class="app-header">
            <div class="app-brand">
                <div class="logo">RH</div>
                <div>
                    <div class="app-title">File a Complaint</div>
                    <div class="app-meta">${sessionScope.userName}</div>
                </div>
            </div>
			<div class="header-actions">
				<form action="cancel"><input class="btn btn-secondary" type="submit" name="cancel" value="Back"></form>
			</div>
		</header>

		<div class="form-card">
			<form action="complaintsubmit" method="post">
				<div class="form-row">
					<label>Name</label>
					<input name="name" type="text" value="${sessionScope.userName}">
				</div>

				<div class="form-row">
					<label>Mobile Number</label>
					<input name="phoneNumber" type="text" value="${sessionScope.phoneNumber}">
				</div>

				<div class="form-row">
					<label>Flat Number</label>
					<input name="flatNo" type="text" value="${sessionScope.flatNumber}">
				</div>

				<div class="form-row">
					<label>Category</label>
					<input name="category" type="text" placeholder="Enter your Complaint category here">
					<p class="helper">${categoryErrorMessage}</p>
				</div>

				<div class="form-row">
					<label>Description of Complaint</label>
					<textarea id="description" name="description" rows="5" placeholder="Enter full details here"></textarea>
					<p class="helper">${descriptionErrorMessage}</p>
				</div>

				<div class="form-row">
					<label for="priority">Choose a priority</label>
					<select name="priority" id="priority">
						<option value="high">High</option>
						<option value="medium">Medium</option>
						<option value="low">Low</option>
					</select>
				</div>

				<div class="form-row">
					<input name="submitComplaint" type="submit" value="Submit" class="btn btn-primary">
				</div>
			</form>
		</div>
	</div>
</body>
</html>