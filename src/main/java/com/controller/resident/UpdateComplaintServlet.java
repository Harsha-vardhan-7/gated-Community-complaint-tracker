package com.controller.resident;

import java.io.IOException;

import com.DAO.ComplaintDAO;
import com.DAO.LoginDAO;
import com.entity.Complaint;
import com.entity.Resident;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateComplaint")
public class UpdateComplaintServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		boolean hasError=false;
		
//		String complaintIdStr = req.getParameter("complaintId");
//		System.out.println("Complaint ID = " + complaintIdStr);
//
//		int complaintId = Integer.parseInt(complaintIdStr);
		
		int complaintId = Integer.parseInt(req.getParameter("complaintId"));

		String phoneNumber = (String) session.getAttribute("phoneNumber");

		String category = req.getParameter("category");
		if (category == null || category.isBlank()) {
			req.setAttribute("categoryErrorMessage", "Category cannot be null or Empty");
			hasError = true;
		}
		
		String description = req.getParameter("description");
		if (description == null || description.isBlank()) {
			req.setAttribute("descriptionErrorMessage", "Description cannot be null or Empty");
			hasError = true;
		}
		
		if (hasError) {
			req.getRequestDispatcher("/WEB-INF/views/editComplaint.jsp").forward(req, resp);
			return;
		}
		
		String priority = req.getParameter("priority");

		LoginDAO loginDAO = new LoginDAO();

		Resident residentDetails = loginDAO.getResidentDetails(phoneNumber);

		ComplaintDAO complaintDAO = new ComplaintDAO();

		Complaint existingComplaint = complaintDAO.getComplaintWithId(complaintId);

		existingComplaint.setCategory(category);
		existingComplaint.setDescription(description);
		existingComplaint.setPriority(priority);

		complaintDAO.updateComplaint(existingComplaint);

		req.setAttribute("complaintId", complaintId);

		req.getRequestDispatcher("/WEB-INF/views/complaintupdatesuccess.jsp").forward(req, resp);
	}
}
