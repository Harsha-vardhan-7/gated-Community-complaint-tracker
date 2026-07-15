package com.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.ComplaintDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminViewComplaintsByStatus")
public class AdminViewComplaintsByStatusServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(AdminViewComplaintsByStatusServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering AdminViewComplaintsByStatusServlet doGet method");

		log.debug("Checking for an active admin session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}

		log.debug("Active session found. Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Fetching complaint status from request parameter");
		String status = req.getParameter("status");

		log.debug("Fetching complaints with status: {}", status);
		List<Complaint> complaintsByStatus = complaintDAO.getComplaintsByStatus(status);
		log.info("Fetched {} complaints with status: {}", complaintsByStatus.size(), status);

		log.debug("Setting filtered complaints as request attribute");
		req.setAttribute("complaints", complaintsByStatus);

		log.debug("Forwarding request to admin complaints page");
		req.getRequestDispatcher("/WEB-INF/views/adminviewcomplaints.jsp").forward(req, resp);

	}
}
