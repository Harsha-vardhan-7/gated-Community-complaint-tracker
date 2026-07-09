package com.controller;

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
		log.trace("Entering into viewComplaintsServlet");

		HttpSession session = req.getSession(false);

		if (session == null) {
		    resp.sendRedirect("login");
		    return;
		}

		log.debug("Fetching the mobile number of the resident");
		String residentMobileNumber = (String) session.getAttribute("phoneNumber");
		log.debug("Mobile number has fetched");

		LoginDAO loginDAO = new LoginDAO();
		Resident resident = loginDAO.getResidentDetails(residentMobileNumber);
		if (resident == null) {
			resp.sendRedirect("login");
			return;
		}

		log.debug("Fetching the Resident Id using the mobile Number");
		int residentId = resident.getResidentId();
		log.debug("Fetched the Resident Id");

		ComplaintDAO complaintDAO = new ComplaintDAO();
		List<Complaint> allComplaints = new ArrayList<>();

		if (resident != null && resident.getRole() == RoleEnum.ADMIN) {
			allComplaints = complaintDAO.getAllComplaints();

		} else if (resident != null && resident.getRole() == RoleEnum.RESIDENT) {
			log.debug("Started fetching the complaints of the resindet with Id: " + residentId);
			allComplaints = complaintDAO.getAllComplaints(residentId);
			log.debug("Fetching of all complaints has done");
		}

		req.setAttribute("complaints", allComplaints);

		log.debug("Forwarding to the viewComplaints.jsp file");
		req.getRequestDispatcher("/WEB-INF/views/residentviewcomplaints.jsp").forward(req, resp);
	}
}
