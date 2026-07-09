package com.controller.resident;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.ComplaintDAO;
import com.DAO.LoginDAO;
import com.entity.Complaint;
import com.entity.Resident;
import com.enums.RoleEnum;

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
		log.trace("Entering ResidentViewComplaintsServlet");

		HttpSession session = req.getSession(false);

		if (session == null) {
			log.warn("No active session found. Redirecting user to login page.");
			resp.sendRedirect("login");
			return;
		}

		log.debug("Fetching the mobile number of the resident");
		String residentMobileNumber = (String) session.getAttribute("phoneNumber");
		log.debug("Mobile number has fetched");

		LoginDAO loginDAO = new LoginDAO();
		Resident resident = loginDAO.getResidentDetails(residentMobileNumber);

		if (resident == null) {
			log.warn("No resident found for mobile number: {}. Redirecting to login page.", residentMobileNumber);
			resp.sendRedirect("login");
			return;
		}

		log.debug("Fetching the Resident Id using the mobile Number");
		int residentId = resident.getResidentId();
		log.debug("Fetched the Resident Id");

		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Started fetching the complaints of the resident with Id: " + residentId);
		List<Complaint> residentComplaints = complaintDAO.getComplaintsByResidentId(residentId);
		log.debug("Fetching of all complaints has done");

		if(residentComplaints.isEmpty()) {
			req.setAttribute("noComplaintsError", "No Complaints present");
		}
		
		req.setAttribute("complaints", residentComplaints);

		log.debug("Forwarding to the residentviewcomplaints.jsp file");
		req.getRequestDispatcher("/WEB-INF/views/residentviewcomplaints.jsp").forward(req, resp);
	}
}
