package com.controller.resident;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger log = LoggerFactory.getLogger(UpdateComplaintServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering UpdateComplaintServlet doPost method");

		log.debug("Fetching active user session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}
		
		boolean hasError = false;
		
		log.debug("Fetching complaint ID from request parameter");
		int complaintId = Integer.parseInt(req.getParameter("complaintId"));
		log.debug("Complaint ID received for update: {}", complaintId);
		
		log.debug("Fetching resident phone number from session");
		String phoneNumber = (String) session.getAttribute("phoneNumber");
		
		log.debug("Fetching updated complaint category from request");
		String category = req.getParameter("category");
		if (category == null || category.isBlank()) {
			log.warn("Complaint update validation failed because category is empty. Complaint ID: {}", complaintId);
			req.setAttribute("categoryErrorMessage", "Category cannot be null or Empty");
			hasError = true;
		}
		
		log.debug("Fetching updated complaint description from request");
		String description = req.getParameter("description");
		if (description == null || description.isBlank()) {
			log.warn("Complaint update validation failed because description is empty. Complaint ID: {}", complaintId);
			req.setAttribute("descriptionErrorMessage", "Description cannot be null or Empty");
			hasError = true;
		}
		
		String priority = req.getParameter("priority");
		log.debug("Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();
		
		log.debug("Fetching existing complaint with ID: {}", complaintId);
		Complaint existingComplaint = complaintDAO.getComplaintWithId(complaintId);
		if (hasError) {
			log.warn("Complaint update failed due to validation errors. Complaint ID: {}", complaintId);
			existingComplaint.setCategory(category);
			existingComplaint.setDescription(description);
			existingComplaint.setPriority(priority);
			req.setAttribute("Complaint", existingComplaint);
			log.debug("Returning complaint data to edit page after validation failure. Complaint ID: {}", complaintId);
			req.getRequestDispatcher("/WEB-INF/views/editComplaint.jsp").forward(req, resp);
			return;
		}
		log.debug("Complaint update validation completed successfully. Complaint ID: {}", complaintId);
		
		log.debug("Creating LoginDAO object and fetching resident details");
		LoginDAO loginDAO = new LoginDAO();
		Resident residentDetails = loginDAO.getResidentDetails(phoneNumber);
		log.debug("Resident details fetched successfully. Resident ID: {}", residentDetails.getResidentId());
		
		log.debug("Existing complaint fetched successfully. Updating category, description and priority");
		existingComplaint.setCategory(category);
		existingComplaint.setDescription(description);
		existingComplaint.setPriority(priority);
		
		log.debug("Persisting updated complaint. Complaint ID: {}", complaintId);
		complaintDAO.updateComplaint(existingComplaint);
		
		log.info("Complaint updated successfully. Complaint ID: {}, Resident ID: {}, Priority: {}",
				complaintId, residentDetails.getResidentId(), priority);
		req.setAttribute("complaintId", complaintId);
		
		log.debug("Forwarding request to complaint update success page");
		req.getRequestDispatcher("/WEB-INF/views/complaintupdatesuccess.jsp").forward(req, resp);
	}
}
