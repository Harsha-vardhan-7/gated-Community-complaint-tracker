package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enums.RoleEnum;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(CancelServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering CancelServlet doGet method");

		log.debug("Checking for an active user session");
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.warn("No active session found. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}
		
		log.debug("Active session found. Fetching user role from session");
		RoleEnum role = (RoleEnum) session.getAttribute("role");
		log.debug("User role fetched from session: {}", role);
		if (role == RoleEnum.ADMIN) {
			log.debug("Admin role identified. Forwarding user to admin dashboard");
			req.getRequestDispatcher("/WEB-INF/views/admindashboard.jsp").forward(req, resp);
			return;
		} else if (role == RoleEnum.RESIDENT) {
			log.debug("Resident role identified. Forwarding user to resident dashboard");
			req.getRequestDispatcher("/WEB-INF/views/residentdashboard.jsp").forward(req, resp);
			return;
		} else {
			log.warn("Invalid or missing user role found in session. Invalidating session and redirecting to login page");
			session.invalidate();
			
			log.debug("Session invalidated successfully. Redirecting user to login page");
			resp.sendRedirect("login");
			return;
		}
	}
}
