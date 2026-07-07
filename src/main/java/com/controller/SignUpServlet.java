package com.controller;

import java.io.IOException;

import com.DAO.LoginDAO;
import com.DAO.ResidentDAO;
import com.entity.Resident;
import com.enums.RoleEnum;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String flatNum = req.getParameter("flatnumber");
		int flatNo;
		if (flatNum == null || flatNum.trim().isEmpty()) {
			req.setAttribute("FlatNoerrorMessage", "Flat Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		} else {
			try {
				flatNo = Integer.parseInt(flatNum);
			} catch (NumberFormatException e) {
				req.setAttribute("flatParseErrorMessage", "Flat Number must contain only numbers.");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
		}

		String mobileNo = req.getParameter("mobile");
		String password = req.getParameter("password");
		String roleStr = req.getParameter("role");
		String adminCode = req.getParameter("admincode");

		if (firstName == null || firstName.trim().isEmpty()) {
			req.setAttribute("firstNameErrorMessage", "First Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			req.setAttribute("lastNameErrorMessage", "Last Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (mobileNo == null || mobileNo.trim().isEmpty()) {
			req.setAttribute("mobileNoErrorMessage", "Mobile Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (password == null || password.trim().isEmpty()) {
			req.setAttribute("passworErrorMessage", "Password is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (roleStr == null || roleStr.trim().isEmpty()) {
			req.setAttribute("roleErrorMessage", "Role is Mandatory, Please Select");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		// Validate admin authentication code if role is ADMIN
		if (roleStr.equalsIgnoreCase("ADMIN")) {
			if (adminCode == null || adminCode.trim().isEmpty()) {
				req.setAttribute("adminCodeErrorMessage", "Admin Authentication Code is Mandatory");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}

			// Verify admin code (you can change this to your desired code)
			final String VALID_ADMIN_CODE = "ADMIN@2024";
			if (!adminCode.equals(VALID_ADMIN_CODE)) {
				req.setAttribute("adminCodeErrorMessage", "Invalid Admin Authentication Code");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
		}

		ResidentDAO resident = new ResidentDAO();

		LoginDAO loginDAO = new LoginDAO();
		Resident residentDetails = loginDAO.getResidentDetails(mobileNo);

		boolean validFlat = (flatNo >= 101 && flatNo <= 120) ||

				(flatNo >= 201 && flatNo <= 220) ||

				(flatNo >= 301 && flatNo <= 320) ||

				(flatNo >= 401 && flatNo <= 420) ||

				(flatNo >= 501 && flatNo <= 520);

		if (!validFlat) {
			req.setAttribute("FlatNoerrorMessage", "Invalid Flat Number. Please enter a valid flat number.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		if (residentDetails == null) {
			resident.saveResident(firstName, lastName, flatNo, mobileNo, password, roleStr);

			req.setAttribute("firstName", firstName);
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}

		if (mobileNo.equalsIgnoreCase(residentDetails.getMobileNumber())) {
			req.setAttribute("errorMessage", "Resident is already registered. Please log in to continue.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

	}

}
