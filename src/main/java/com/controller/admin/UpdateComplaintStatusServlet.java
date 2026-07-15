package com.controller.admin;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.ComplaintDAO;
import com.controller.LoginServlet;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateComplaintStatus")
public class UpdateComplaintStatusServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(UpdateComplaintStatusServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.trace("Entering UpdateComplaintStatusServlet doPost method");

		log.debug("Checking for an active admin session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}
		log.debug("Active session found. Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Fetching new complaint status and complaint ID from request parameters");
		String status = (String) req.getParameter("status");

		int complaintId = Integer.parseInt(req.getParameter("complaintId"));
		log.debug("Fetching complaint with ID: {}", complaintId);

		Complaint complaint = complaintDAO.getComplaintWithId(complaintId);
		log.debug("Updating complaint ID {} status from '{}' to '{}'", complaintId, complaint.getStatus(), status);

		complaint.setStatus(status);

		complaintDAO.updateComplaint(complaint);
		log.info("Complaint status updated successfully. Complaint ID: {}, New Status: {}", complaintId, status);
		log.debug("Redirecting admin to all complaints page");

		resp.sendRedirect("adminViewComplaints");
	}
}
