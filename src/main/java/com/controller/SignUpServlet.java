package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DAO.LoginDAO;
import com.DAO.ResidentDAO;
import com.entity.Resident;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(SignUpServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering SignUpServlet doGet method");

		log.debug("Forwarding user to registration page");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Entering SignUpServlet doPost method");

		log.debug("Fetching resident registration details from request");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String flatNum = req.getParameter("flatnumber");
		int flatNo;
		if (flatNum == null || flatNum.trim().isEmpty()) {
			log.warn("Resident registration failed because flat number is empty");
			req.setAttribute("FlatNoerrorMessage", "Flat Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		} else {
			try {
				log.debug("Parsing flat number from request");
				flatNo = Integer.parseInt(flatNum);
			} catch (NumberFormatException e) {
				log.warn("Resident registration failed because flat number contains invalid characters");
				req.setAttribute("flatParseErrorMessage", "Flat Number must contain only numbers.");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
		}
		
		String mobileNo = req.getParameter("mobile");
		String password = req.getParameter("password");
		String roleStr = req.getParameter("role");
		String adminCode = req.getParameter("admincode");
		
		log.debug("Starting mandatory field validation for resident registration");
		
		if (firstName == null || firstName.trim().isEmpty()) {
			log.warn("Resident registration failed because first name is empty");
			req.setAttribute("firstNameErrorMessage", "First Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			log.warn("Resident registration failed because last name is empty");
			req.setAttribute("lastNameErrorMessage", "Last Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (mobileNo == null || mobileNo.trim().isEmpty()) {
			log.warn("Resident registration failed because mobile number is empty");
			req.setAttribute("mobileNoErrorMessage", "Mobile Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (password == null || password.trim().isEmpty()) {
			log.warn("Resident registration failed because password is empty");
			req.setAttribute("passworErrorMessage", "Password is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (roleStr == null || roleStr.trim().isEmpty()) {
			log.warn("Resident registration failed because role was not selected");
			req.setAttribute("roleErrorMessage", "Role is Mandatory, Please Select");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		log.debug("Mandatory field validation completed successfully");
		
		// Validate admin authentication code if role is ADMIN
		if (roleStr.equalsIgnoreCase("ADMIN")) {
			log.debug("Admin registration detected. Starting admin authentication code validation");
			if (adminCode == null || adminCode.trim().isEmpty()) {
				log.warn("Admin registration failed because admin authentication code is empty");
				req.setAttribute("adminCodeErrorMessage", "Admin Authentication Code is Mandatory");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
			
			// Verify admin code (you can change this to your desired code)
			final String VALID_ADMIN_CODE = "ADMIN@2024";
			if (!adminCode.equals(VALID_ADMIN_CODE)) {
				log.warn("Admin registration failed because an invalid admin authentication code was provided");
				req.setAttribute("adminCodeErrorMessage", "Invalid Admin Authentication Code");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
			log.debug("Admin authentication code validation successful");
		}
		
		log.debug("Creating ResidentDAO object");
		ResidentDAO resident = new ResidentDAO();
		
		log.debug("Creating LoginDAO object and checking whether resident is already registered");
		LoginDAO loginDAO = new LoginDAO();
		
		Resident residentDetails = loginDAO.getResidentDetails(mobileNo);
		
		log.debug("Validating flat number against permitted community flat ranges");
		boolean validFlat = (flatNo >= 101 && flatNo <= 120) || (flatNo >= 201 && flatNo <= 220)
				|| (flatNo >= 301 && flatNo <= 320) || (flatNo >= 401 && flatNo <= 420)
				|| (flatNo >= 501 && flatNo <= 520);
		if (!validFlat) {
			log.warn("Resident registration failed because an invalid flat number was provided: {}", flatNo);
			req.setAttribute("FlatNoerrorMessage", "Invalid Flat Number. Please enter a valid flat number.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		
		log.debug("Flat number validation successful for flat number: {}", flatNo);
		if (residentDetails == null) {
			log.debug("No existing resident found. Saving new resident registration");
			resident.saveResident(firstName, lastName, flatNo, mobileNo, password, roleStr);
			
			log.info("Resident registered successfully. Flat Number: {}, Role: {}", flatNo, roleStr);
			req.setAttribute("firstName", firstName);
			log.debug("Forwarding newly registered user to login page");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}
		if (mobileNo.equalsIgnoreCase(residentDetails.getMobileNumber())) {
			log.warn("Registration attempt failed because resident is already registered");
			req.setAttribute("errorMessage", "Resident is already registered. Please log in to continue.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

	}

}
