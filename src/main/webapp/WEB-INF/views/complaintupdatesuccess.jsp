<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Complaint Update Successful </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
    <div class="container">
        <header class="app-header">
            <div class="app-brand">
                <div class="logo">RH</div>
                <div>
                    <div class="app-title">Complaint Updated</div>
                </div>
            </div>
        </header>

		<div class="card center">
			<h2>Update Successful</h2>
			<p>Hi ${userName}, your complaint has been updated (Complaint Id: <strong>${complaintId}</strong>).</p>
			<form action="cancel">
				<input type="hidden" name="userName" value="${sessionScope.userName}">
				<input name="cancel" type="submit" value="Return to Home" class="btn btn-primary">
			</form>
		</div>
	</div>
</body>
</html>