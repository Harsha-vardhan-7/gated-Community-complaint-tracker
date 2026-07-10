<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Complaint</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body>
    <div class="container">
        <header class="app-header">
            <div class="app-brand">
                <div class="logo">RH</div>
                <div>
                    <div class="app-title">Edit Complaint</div>
                    <div class="app-meta">${sessionScope.userName}</div>
                </div>
            </div>
            <div class="header-actions">
                <form action="residentViewComplaints"><input class="btn btn-secondary" type="submit" name="cancel" value="Back"></form>
            </div>
        </header>

        <div class="form-card">
            <form action="updateComplaint" method="post">
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
                    <input name="category" type="text" value="${Complaint.category}">
                    <p class="helper">${categoryErrorMessage}</p>
                </div>

                <div class="form-row">
                    <label>Description of Complaint</label>
                    <textarea id="description" name="description" rows="5">${Complaint.description}</textarea>
                    <p class="helper">${categoryErrorMessage}</p>
                </div>

                <div class="form-row">
                    <label for="priority">Choose a priority</label>
                    <select name="priority" id="priority">
                        <option value="high" ${Complaint.priority == 'high' ? 'selected' : ''}>High</option>
                        <option value="medium" ${Complaint.priority == 'medium' ? 'selected' : ''}>Medium</option>
                        <option value="low" ${Complaint.priority == 'low' ? 'selected' : ''}>Low</option>
                    </select>
                </div>

                <div class="form-row">
                    <input name="submitComplaint" type="submit" value="Submit" class="btn btn-primary">
                    <input type="hidden" name="complaintId" value="${Complaint.complaintId}">
                </div>
            </form>
        </div>
    </div>
</body>
</html>