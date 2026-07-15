package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.LoginDAO;
import com.entity.Resident;
import com.enums.RoleEnum;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.trace("Entering LoginServlet doPost method");
		log.debug("Fetching login credentials from request");
		String mobileNumber = req.getParameter("username");
		String password = req.getParameter("password");
		if (mobileNumber == null || mobileNumber.isBlank() || password == null || password.isBlank()) {
			log.warn("Login attempt failed because mobile number or password is empty");
			req.setAttribute("emptyFields", "Please enter mobile number and password.");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}

		log.debug("Creating LoginDAO object");
		LoginDAO login = new LoginDAO();

		log.debug("Fetching resident details for login authentication");
		Resident resident = login.getResidentDetails(mobileNumber);
		if (resident == null) {
			log.warn("Login attempt failed. No resident found for the provided mobile number");
			req.setAttribute("errorMessage", "Username or Password is incorrect");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}

		log.debug("Resident found. Starting password validation");
		String resPassword = resident.getPassword();
		if (password.equals(resPassword)) {
			log.debug("Password validation successful. Creating user session");
			HttpSession session = req.getSession();
			session.setAttribute("residentId", resident.getResidentId());
			session.setAttribute("userName", resident.getFirstName() + " " + resident.getLastName());
			session.setAttribute("phoneNumber", resident.getMobileNumber());
			session.setAttribute("flatNumber", resident.getFlatNumber());
			session.setAttribute("role", resident.getRole());
			
		log.info("User logged in successfully. Resident ID: {}, Role: {}", resident.getResidentId(),resident.getRole());
		
			if (resident.getRole() == RoleEnum.RESIDENT) {
				log.debug("Resident role identified. Forwarding user to resident dashboard");
				req.getRequestDispatcher("/WEB-INF/views/residentdashboard.jsp").forward(req, resp);
				return;
			} else if (resident.getRole() == RoleEnum.ADMIN) {
				log.debug("Admin role identified. Forwarding user to admin dashboard");
				req.getRequestDispatcher("/WEB-INF/views/admindashboard.jsp").forward(req, resp);
				return;
			} else {
				log.warn("Invalid or missing role found for resident ID: {}", resident.getResidentId());
				req.setAttribute("roleErrorMessage", "Invalid user role");
				req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			}
		} else {
			log.warn("Login attempt failed due to invalid password");
			req.setAttribute("errorMessage", "Username or Password is incorrect");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}
}
