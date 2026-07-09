package com.controller.resident;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.LoginDAO;
import com.entity.Resident;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/raiseComplaint")
public class RaiseComplaintServlet extends HttpServlet{

	private static final Logger log =LoggerFactory.getLogger(RaiseComplaintServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("Entered into RaiseComplaintServlet");

		HttpSession session = req.getSession(false);

		if (session == null) {
		    resp.sendRedirect("login");
		    return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/complaintfiling.jsp").forward(req, resp);
		log.debug("RaiseComplaintServlet executed");
	}
	
}
