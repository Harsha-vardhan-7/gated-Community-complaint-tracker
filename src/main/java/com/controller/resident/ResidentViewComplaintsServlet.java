package com.controller.resident;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/residentViewComplaints")
public class ResidentViewComplaintsServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(ResidentViewComplaintsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering ResidentViewComplaintsServlet doGet method");

		log.debug("Checking for an active user session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}

		log.debug("Active session found. Fetching resident mobile number from session");
		String residentMobileNumber = (String) session.getAttribute("phoneNumber");

		log.debug("Creating LoginDAO object and fetching resident details");
		LoginDAO loginDAO = new LoginDAO();
		Resident resident = loginDAO.getResidentDetails(residentMobileNumber);
		if (resident == null) {
			log.warn("No resident found for the mobile number stored in session. Redirecting to login page");
			resp.sendRedirect("login");
			return;
		}

		log.debug("Resident details fetched successfully. Resident ID: {}", resident.getResidentId());
		int residentId = resident.getResidentId();

		log.debug("Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Fetching complaints for resident ID: {}", residentId);
		List<Complaint> residentComplaints = complaintDAO.getComplaintsByResidentId(residentId);
		log.info("Fetched {} complaints for resident ID: {}", residentComplaints.size(), residentId);
		if (residentComplaints.isEmpty()) {
			log.debug("No complaints found for resident ID: {}", residentId);
			req.setAttribute("noComplaintsError", "No Complaints present");
		}

		log.debug("Setting resident complaints as request attribute");
		req.setAttribute("complaints", residentComplaints);

		log.debug("Forwarding request to resident complaints page");
		req.getRequestDispatcher("/WEB-INF/views/residentviewcomplaints.jsp").forward(req, resp);
	}
}
