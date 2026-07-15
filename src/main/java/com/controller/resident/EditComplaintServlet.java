package com.controller.resident;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.ComplaintDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditComplaintServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(EditComplaintServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering EditComplaintServlet doGet method");

		log.debug("Fetching complaint ID from request parameter");
		int complaintId = Integer.parseInt(req.getParameter("complaintId"));
		log.debug("Complaint ID received for editing: {}", complaintId);

		log.debug("Creating ComplaintDAO object");
		ComplaintDAO complaintDAO = new ComplaintDAO();

		log.debug("Fetching complaint details for complaint ID: {}", complaintId);
		Complaint complaint = complaintDAO.getComplaintWithId(complaintId);

		log.debug("Complaint details fetched successfully for complaint ID: {}", complaintId);
		log.debug("Setting complaint as request attribute");
		req.setAttribute("Complaint", complaint);

		log.debug("Forwarding request to edit complaint page");
		req.getRequestDispatcher("/WEB-INF/views/editComplaint.jsp").forward(req, resp);
	}

}
