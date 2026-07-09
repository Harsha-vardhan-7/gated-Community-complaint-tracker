package com.controller.admin;

import java.io.IOException;

import com.DAO.ComplaintDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateComplaintStatusServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		if (session == null) {
			resp.sendRedirect("login");
			return;
		}
		
		String status=(String)session.getAttribute("status");
		
		ComplaintDAO complaintDAO = new ComplaintDAO();
		
	}
}
