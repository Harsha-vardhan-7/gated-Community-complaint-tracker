package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering LogoutServlet doGet method");

		log.debug("Checking for an active user session");
		HttpSession session = req.getSession(false);
		if (session != null) {
			log.debug("Active session found. Invalidating user session");
			session.invalidate();
			log.info("User logged out successfully and session invalidated");
		} else {
			log.warn("Logout requested but no active session was found");
		}

		log.debug("Forwarding user to login page");
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}

}
