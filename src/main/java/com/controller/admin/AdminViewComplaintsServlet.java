package com.controller.admin;

import java.io.IOException;
import java.util.List;

import com.DAO.ComplaintDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminViewComplaints")
public class AdminViewComplaintsServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if (session == null) {
//			log.warn("No active session found. Redirecting user to login page.");
			resp.sendRedirect("login");
			return;
		}
		
		ComplaintDAO complaintDAO = new ComplaintDAO();
		
		List<Complaint> complaints = complaintDAO.getAllComplaints();
		
		req.setAttribute("complaints", complaints);
		
		req.getRequestDispatcher("/WEB-INF/views/adminviewcomplaints.jsp").forward(req, resp);
		
	}
}
