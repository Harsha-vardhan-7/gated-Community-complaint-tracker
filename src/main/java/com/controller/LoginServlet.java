package com.controller;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final Logger log =LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering into LoginServlet");
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		
		log.trace("LoginDAO object creation started");
		LoginDAO login = new LoginDAO();
		
		log.trace("LoginDAO object has been created");

		Resident resident = login.getResidentDetails(userName);
		
		log.trace("Got Resident details from LoginDAO");
		
		if(resident==null) {
			req.setAttribute("errorMessage", "Username or Password is incorrect");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
		
		log.trace("Extracting password from Resident details");
		String resPassword = resident.getPassword();
		
		log.debug("Password Validation started");
		
		if( password!=null && password.equals(resPassword)) {
			log.debug("Password validation successfull and redirecting to Dashboard");
			req.setAttribute("userName", resident.getFirstName());
	
			req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
		}else {
			log.debug("Password Validation failed, returning to login page");
			req.setAttribute("errorMessage", "Username or Password is incorrect");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}
}
