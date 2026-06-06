package com.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.LoginDAO;
import com.DAO.ViewComplaintsDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewComplaints")
public class ViewComplaintsServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(ViewComplaintsServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering into viewComplaintsServlet");
		
		HttpSession session = req.getSession();
		
		log.debug("Fetching the mobile number of the resident");
		String residentMobileNumber=(String)session.getAttribute("phoneNumber");
		log.debug("Mobile number has fetched");
		
		LoginDAO loginDAO= new LoginDAO();
		log.debug("Fetching the Resident Id using the mobile Number");
		int residentId = loginDAO.getResidentId(residentMobileNumber);
		log.debug("Fetched the Resident Id");
		
		ViewComplaintsDAO viewComplaintsDAO= new ViewComplaintsDAO();
		
		log.debug("Started fetching the complaints of the resindet with Id: "+ residentId);
		List<Complaint> allComplaints = viewComplaintsDAO.getAllComplaints(residentId);
		log.debug("Fetching of all complaints has done");
		
		req.setAttribute("complaints", allComplaints);
		
		log.debug("Forwarding to the viewComplaints.jsp file");
		req.getRequestDispatcher("/WEB-INF/views/viewcomplaints.jsp").forward(req, resp);
	}
}
