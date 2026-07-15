package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginredirect")
public class LoginRedirectServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LoginRedirectServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering LoginRedirectServlet doGet method");

		log.debug("Forwarding request to login page");
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
}
