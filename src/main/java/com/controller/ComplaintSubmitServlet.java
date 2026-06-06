package com.controller;

import java.io.IOException;

import com.DAO.ComplaintSubmitDAO;
import com.DAO.LoginDAO;
import com.entity.Complaint;
import com.entity.Resident;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/complaintsubmit")
public class ComplaintSubmitServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		String phoneNumber = (String) session.getAttribute("phoneNumber");

		String category = req.getParameter("category");
		String description = req.getParameter("description");
		String priority = req.getParameter("priority");

		LoginDAO loginDAO = new LoginDAO();

		Resident residentDetails = loginDAO.getResidentDetails(phoneNumber);

		ComplaintSubmitDAO compSubDAO = new ComplaintSubmitDAO();

		Complaint comp = new Complaint();

		comp.setCategory(category);
		comp.setDescription(description);
		comp.setPriority(priority);
		comp.setStatus("Pending");
		comp.setResident(residentDetails);

		compSubDAO.saveComplaint(comp);

		req.setAttribute("complaintId", comp.getComplaintId());

		req.getRequestDispatcher("/WEB-INF/views/complaintsuceess.jsp").forward(req, resp);
	}

}
