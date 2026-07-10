package com.controller.admin;

import java.io.IOException;

import com.DAO.ComplaintDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateComplaintStatus")
public class UpdateComplaintStatusServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendRedirect("login");
			return;
		}
		
		ComplaintDAO complaintDAO = new ComplaintDAO();
		
		String status = (String) req.getParameter("status");
	    int complaintId = Integer.parseInt(req.getParameter("complaintId"));
		
		Complaint complaint = complaintDAO.getComplaintWithId(complaintId);
		complaint.setStatus(status);
		
		complaintDAO.updateComplaint(complaint);
		
		resp.sendRedirect("adminViewComplaints");
	}
}
