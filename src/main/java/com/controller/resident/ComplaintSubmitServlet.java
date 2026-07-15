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

@WebServlet("/complaintsubmit")
public class ComplaintSubmitServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(ComplaintSubmitServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering ComplaintSubmitServlet doPost method");

		log.debug("Fetching active user session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}

		boolean hasError = false;
		log.debug("Fetching resident phone number from session");

		String phoneNumber = (String) session.getAttribute("phoneNumber");

		log.debug("Fetching complaint category from request");
		String category = req.getParameter("category");
		if (category == null || category.isBlank()) {
			log.warn("Complaint submission validation failed because category is empty");
			req.setAttribute("categoryErrorMessage", "Category cannot be null or Empty");
			hasError = true;
		}

		log.debug("Fetching complaint description from request");
		String description = req.getParameter("description");
		if (description == null || description.isBlank()) {
			log.warn("Complaint submission validation failed because description is empty");
			req.setAttribute("descriptionErrorMessage", "Description cannot be null or Empty");
			hasError = true;
		}
		if (hasError) {
			log.warn("Complaint submission failed due to validation errors. Forwarding back to complaint filing page");
			req.getRequestDispatcher("/WEB-INF/views/complaintfiling.jsp").forward(req, resp);
			return;
		}

		log.debug("Complaint validation completed successfully");
		String priority = req.getParameter("priority");

		log.debug("Creating LoginDAO object and fetching resident details");
		LoginDAO loginDAO = new LoginDAO();

		Resident residentDetails = loginDAO.getResidentDetails(phoneNumber);
		log.debug("Resident details fetched successfully. Resident ID: {}", residentDetails.getResidentId());

		log.debug("Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Creating new Complaint object");
		Complaint comp = new Complaint();
		comp.setCategory(category);
		comp.setDescription(description);
		comp.setPriority(priority);
		comp.setStatus("Pending");
		comp.setResident(residentDetails);

		log.debug("Saving new complaint for resident ID: {}", residentDetails.getResidentId());
		complaintDAO.saveComplaint(comp);

		log.info("Complaint submitted successfully. Complaint ID: {}, Resident ID: {}, Priority: {}",
				comp.getComplaintId(), residentDetails.getResidentId(), priority);
		req.setAttribute("complaintId", comp.getComplaintId());

		log.debug("Forwarding request to complaint success page");
		req.getRequestDispatcher("/WEB-INF/views/complaintsuccess.jsp").forward(req, resp);
	}

}
