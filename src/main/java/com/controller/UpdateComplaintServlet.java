package com.controller;

import java.io.IOException;

import com.DAO.ComplaintDAO;
import com.entity.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class UpdateComplaintServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int complaintId = Integer.parseInt(req.getParameter("complaintId"));
		
		ComplaintDAO complaintDAO= new ComplaintDAO();
		Complaint complaint = complaintDAO.getComplaintWithId(complaintId);
		
		req.setAttribute("Complaint", complaint);
		
		req.getRequestDispatcher("/WEB-INF/views/editComplaint.jsp").forward(req, resp);
	}
}
