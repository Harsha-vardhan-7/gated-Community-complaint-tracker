package com.controller.resident;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/raiseComplaint")
public class RaiseComplaintServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(RaiseComplaintServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering RaiseComplaintServlet doGet method");

		log.debug("Checking for an active user session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}
		log.debug("Active session found. Forwarding user to complaint filing page");

		req.getRequestDispatcher("/WEB-INF/views/complaintfiling.jsp").forward(req, resp);
		log.trace("RaiseComplaintServlet doGet method completed");
	}

}
